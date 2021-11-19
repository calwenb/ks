package wen.dao;

import wen.pojo.Order;
import wen.pojo.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDao implements BaseDao {
    @Override
    public int addTarget(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
        return BaseDao.super.addTarget(con, sql, target);
    }

    @Override
    public int updateTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        return BaseDao.super.updateTarget(con, sql, setSqls);
    }

    public static ArrayList<OrderItem> queryAllOrderItem(Connection con) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        return orderItems;
    }

    public static ArrayList<OrderItem> queryAllOrderItemById(Connection con, int id) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        String sql = "select * from shop.order_item where order_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            orderItems.add(new OrderItem(rs.getInt("order_id"), rs.getString("goods_name"), rs.getString("goods_size"), rs.getInt("buy_num")));
        }
        return orderItems;
    }

    public static int delOrderItem(Connection con, int id) throws SQLException {
        String sql = "delete from shop.order_item where order_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        return pst.executeUpdate();
    }
}
