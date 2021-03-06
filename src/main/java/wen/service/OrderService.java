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
     * ????????????
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
            //??????????????????
            sql = "INSERT INTO `order`(login_name,linkman,address,phonenumber,amount,time,remark,status) VALUES ( ?, ?, ?, ?,?, ?,?,?)";
            int i = orderDao.addTarget(conn, sql, order);
            int orderId = OrderDao.queryMaxId(conn); //?????????????????????id
            //??????????????????????????????????????????
            //??????????????????
            for (Cart cart : carts) {
                //????????????????????????
                sql = "SELECT * FROM shop.`goods` where id=?";
                setSqls = new Object[]{cart.getpId()};
                Goods goods = (Goods) goodsDao.queryTarget(conn, sql, Goods.class, setSqls);
                int pnum = goods.getPnum();
                //System.out.println(pnum + " " + cart.getBuyNum());
                if (pnum < cart.getBuyNum()) {//????????????
                    //System.out.println("????????????");
                    conn.rollback();
                    conn.close();
                    return 0;
                }
                //????????????
                sql = "UPDATE shop.`goods`  SET `goods`.pnum=? WHERE `goods`.id=?";
                setSqls = new Object[]{goods.getPnum() - cart.getBuyNum(), goods.getId()};
                goodsDao.updateTarget(conn, sql, setSqls);

                //??????????????????????????????????????????
                sql = "INSERT INTO `order_item` VALUES (?,?,?,?)";
                OrderItem orderItem = new OrderItem(orderId, cart.getGoodsName(), goods.getSize(), cart.getBuyNum());
                orderItemDao.addTarget2(conn, sql, orderItem);
                //?????????????????????
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
     * ???????????????,
     * ??????queryAllOrders????????????,?????????
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
            myGoodsMsg.append("?????????:" + orderItem.getGoodsName() + "??????:" + orderItem.getSize() + "????????????:" + orderItem.getBuyNum());
        }
        String myName = "??????????????????";
        String subject = order.getLogin_name() + ":??????????????????";
        String orderSatus = "????????????";
        if (order.getStatus() == 1) {
            orderSatus = "????????????";
        } else if (order.getStatus() == 2) {
            orderSatus = "???????????????";
        }
        String content = "??????????????????:" + order.getId() + "<br>" +
                "???????????????:" + myGoodsMsg + "<br>" +
                "????????????:" + "<br>" +
                "?????????:" + order.getLinkman() + "<br>" + "??????:" + order.getAddress() + "<br>" + "????????????:" + order.getPhonenumber() + "<br>" +
                "????????????:" + order.getTime() + "<br>" +
                "??????????????????:" + orderSatus + "<br>" +
                "??????????????????!";
        MailUtil.sendMail(orderMail, myName, subject, content);
    }
}
