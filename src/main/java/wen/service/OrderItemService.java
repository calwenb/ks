package wen.service;

import wen.dao.OrderItemDao;
import wen.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderItemService {

    public static int delOrderItem(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        int i = OrderItemDao.delOrderItem(con, orderId);
        con.close();
        return i;
    }
}
