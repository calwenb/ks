package wen.service;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import wen.dao.OrderDao;
import wen.pojo.Order;
import wen.utils.JDBCUtil;
import wen.utils.MailUtil;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        return OrderDao.queryAllOrders(con, preNum);
    }

    public static ArrayList<Order> queryLike(String queryText, boolean isLike, int preNum) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        if (isLike) {
            queryText = "%" + queryText + "%";
        }
        return OrderDao.queryLike(con, queryText, isLike, preNum);
    }

    public static int delOrder(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return OrderDao.delOrder(con, orderId);
    }

    public static Order queryAllOrderById(int id) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return OrderDao.queryAllOrderById(con, id);
    }

    public static int updateOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return OrderDao.update(con, order);
    }

    /**
     * 通过反射修改操作数,
     * 改变queryAllOrders执行逻辑,不分页
     *
     * @return json
     */
    public static String orderCount() throws Exception {
        Connection con = JDBCUtil.getConnection();
        Class<OrderDao> orderDaoClass = OrderDao.class;
        Field action = orderDaoClass.getDeclaredField("action");
        action.setAccessible(true);
        action.set(boolean.class, false);
        Method orderDCMethod = orderDaoClass.getMethod("queryAllOrders", Connection.class, int.class);
        ArrayList<Order> orders = (ArrayList<Order>) orderDCMethod.invoke(OrderDao.class, con, -1);
        action.set(boolean.class, true);
        //
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
        rsMap.put("yearAmount",String.valueOf(yearAmount));
        rsMap.put("monthAmount",String.valueOf(monthAmount));
        //System.out.println(rsMap);
        return JSON.toJSONString(rsMap);
    }

    public static void sendMail(String orderMail) throws MessagingException, GeneralSecurityException, UnsupportedEncodingException {
        MailUtil.sendMail(orderMail, "文", "下单成功", "您下单成功,请关注物流信息");
    }
}
