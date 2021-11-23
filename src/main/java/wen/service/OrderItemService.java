package wen.service;

import wen.dao.OrderDao;
import wen.dao.OrderItemDao;
import wen.pojo.Order;
import wen.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemService {
   /* public static ArrayList<Order> queryAllOrders(int preNum) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return OrderDao.queryAllOrders(con,preNum);
    }*/
    public static int delOrderItem(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return OrderItemDao.delOrderItem(con,orderId);
    }
}
