package wen.service;

import com.alibaba.fastjson.JSON;
import huang.dao.CartDao;
import huang.pojo.Cart;
import pan.dao.GoodsDao;
import pan.pojo.Goods;
import wen.dao.OrderDao;
import wen.dao.OrderItemDao;
import wen.pojo.Order;
import wen.pojo.OrderItem;
import wen.utils.JDBCUtil;
import wen.utils.MailUtil;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class OrderService {
    public static ArrayList<Order> queryAllOrders(int preNum) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        ArrayList<Order> orders = OrderDao.queryAllOrders(con, preNum);
        con.close();
        return orders;
    }

    public static ArrayList<Order> queryAllMyOrders(int preNum, String loginName) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        ArrayList<Order> orders = OrderDao.queryAllOrders(con, preNum, loginName);
        con.close();
        return orders;
    }

    public static ArrayList<Order> queryLike(String queryText, boolean isLike, int preNum) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        if (isLike) {
            queryText = "%" + queryText + "%";
        }
        ArrayList<Order> orders = OrderDao.queryLike(con, queryText, isLike, preNum);
        con.close();
        return orders;
    }

    public static int delOrder(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        int i = OrderDao.delOrder(con, orderId);
        con.close();
        return i;
    }

    public static Order queryOrderById(int id) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        Order order = OrderDao.queryOrderById(con, id);
        con.close();
        return order;
    }

    public static int updateOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        int i = OrderDao.update(con, order);
        con.close();
        return i;
    }

    /**
     * 下单业务
     *
     * @param order
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public static int addOrder(Order order, ArrayList<Cart> carts) throws SQLException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connection conn = JDBCUtil.getConnection();
        conn.setAutoCommit(false);
        CartDao cartDao = new CartDao();
        OrderItemDao orderItemDao = new OrderItemDao();
        OrderDao orderDao = new OrderDao();
        GoodsDao goodsDao = new GoodsDao();
        String sql = "";
        Object[] setSqls;
        try {
            //订单表的增加
            sql = "INSERT INTO `order`(login_name,linkman,address,phonenumber,amount,time,remark,status) VALUES ( ?, ?, ?, ?,?, ?,?,?)";
            int i = orderDao.addTarget(conn, sql, order);
            int orderId = OrderDao.queryMaxId(conn); //查询刚刚自增的id
            //将购物车的商品加入订单详细表
            //且删除购物车
            for (Cart cart : carts) {
                //判断库存是否足够
                sql = "SELECT * FROM shop.`goods` where id=?";
                setSqls = new Object[]{cart.getpId()};
                Goods goods = (Goods) goodsDao.queryTarget(conn, sql, Goods.class, setSqls);
                int pnum = goods.getPnum();
                //System.out.println(pnum + " " + cart.getBuyNum());
                if (pnum < cart.getBuyNum()) {//库存不够
                    //System.out.println("库存不够");
                    conn.rollback();
                    conn.close();
                    return 0;
                }
                //库存减一
                sql = "UPDATE shop.`goods`  SET `goods`.pnum=? WHERE `goods`.id=?";
                setSqls = new Object[]{goods.getPnum() - 1, goods.getId()};
                goodsDao.updateTarget(conn, sql, setSqls);
                //将购物车的商品加入订单详细表
                sql = "INSERT INTO `order_item` VALUES (?,?,?,?)";
                OrderItem orderItem = new OrderItem(orderId, cart.getGoodsName(), goods.getSize(), cart.getBuyNum());
                orderItemDao.addTarget2(conn, sql, orderItem);
                //购物车表的删除
                sql = "delete from shop.cart where cart_id=?";
                setSqls = new Object[]{cart.getCartId()};
                cartDao.deleteTarget(conn, sql, setSqls);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            return 0;
        } finally {
            conn.close();
        }
        return 1;
    }

    /**
     * 修改操作数,
     * 改变queryAllOrders执行逻辑,不分页
     *
     * @return json
     */
    public static String orderCount() throws Exception {
        Connection con = JDBCUtil.getConnection();
        OrderDao.action = false;
        ArrayList<Order> orders = OrderDao.queryAllOrders(con, -1);
        OrderDao.action = true;

        int[] a = new int[12];
        double yearAmount = 0;
        double monthAmount = 0;
        for (Order order : orders) {
            if (order.getTime().getYear() == new Date().getYear()) {
                yearAmount += order.getAmount();
                int month = order.getTime().getMonth();
                a[month]++;
            }
        }
        //////////
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Order order : orders) {
            Date time = order.getTime();
            Date nowDate = new Date();
            if (time.getYear() == nowDate.getYear() && time.getMonth() == nowDate.getMonth()) {
                monthAmount += order.getAmount();
                int day = order.getTime().getDate();
                map.put(day, map.getOrDefault(day, 0) + 1);
            }
        }
        Set<Integer> keySet = map.keySet();
        ArrayList month_x = new ArrayList();
        ArrayList month_y = new ArrayList();
        for (Integer key : keySet) {
            month_x.add(key);
            month_y.add(map.get(key));
            //System.out.println(key + " " + map.get(key));
        }
        HashMap<String, String> rsMap = new HashMap<>();
        rsMap.put("year", JSON.toJSONString(a));
        rsMap.put("month_x", String.valueOf(month_x));
        rsMap.put("month_y", String.valueOf(month_y));
        rsMap.put("yearAmount", String.format("%.2f", yearAmount));
        rsMap.put("monthAmount", String.format("%.2f", monthAmount));
        con.close();
        return JSON.toJSONString(rsMap);
    }

    public static int OrderFinish(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        OrderDao orderDao = new OrderDao();
        String sql = "UPDATE `order` SET `status`=2 WHERE id=?";
        Object[] setSqls = {orderId};
        int i = orderDao.updateTarget(con, sql, setSqls);
        con.close();
        return i;
    }

    public static void sendMail(int orderId, String orderMail) throws MessagingException, GeneralSecurityException, UnsupportedEncodingException, SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        Order order = OrderDao.queryOrderById(con, orderId);
        ArrayList<OrderItem> orderItems = OrderItemDao.queryAllOrderItemById(con, orderId);
        StringBuffer myGoodsMsg = new StringBuffer();
        for (OrderItem orderItem : orderItems) {
            myGoodsMsg.append("商品名:" + orderItem.getGoodsName() + "尺码:" + orderItem.getSize() + "购买数量:" + orderItem.getBuyNum());
        }
        String myName = "光宗耀组商城";
        String subject = order.getLogin_name() + ":您的订单信息";
        String orderSatus = "无效下单";
        if (order.getStatus() == 1) {
            orderSatus = "已经下单";
        } else if (order.getStatus() == 2) {
            orderSatus = "已完成订单";
        }
        String content = "您的订单编号:" + order.getId() + "<br>" +
                "购买的商品:" + myGoodsMsg + "<br>" +
                "通信地址:" + "<br>" +
                "联系人:" + order.getLinkman() + "<br>" + "地址:" + order.getAddress() + "<br>" + "电话号码:" + order.getPhonenumber() + "<br>" +
                "下单时间:" + order.getTime() + "<br>" +
                "当前订单状态:" + orderSatus + "<br>" +
                "欢迎下次光临!";
        MailUtil.sendMail(orderMail, myName, subject, content);
    }
}
