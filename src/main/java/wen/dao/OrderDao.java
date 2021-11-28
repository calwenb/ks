package wen.dao;

import wen.pojo.Order;
import wen.pojo.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements BaseDao {
    //给反射一个操作通道
    public static boolean action = true;

    //通过操作数是否分页 ...其他条件
    public static ArrayList<Order> queryAllOrders(Connection con, int preNum, String... condition) throws SQLException {
        int start = (preNum - 1) * 10;
        ArrayList<Order> orders = new ArrayList<>();
        boolean isLimit = action;
        String sql;
        PreparedStatement pst;
        if (isLimit) {  //是否分页
            if (condition.length > 0) {
                sql = "select * from shop.`order` where login_name=? limit ?,10";
                pst = con.prepareStatement(sql);
                pst.setString(1, condition[0]);
                pst.setInt(2, start);
            } else {
                sql = "select * from shop.`order`  limit ?,10";
                pst = con.prepareStatement(sql);
                pst.setInt(1, start);
            }
        } else {
            sql = "select * from shop.`order`";
            pst = con.prepareStatement(sql);
        }
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            List<OrderItem> orderItems = OrderItemDao.queryAllOrderItemById(con, rs.getInt("id"));
            orders.add(new Order(rs.getInt("id"), rs.getString("login_name"), rs.getString("linkman"), rs.getString("address"), rs.getString("phonenumber"), rs.getDouble("amount"), rs.getTimestamp("time"), rs.getString("remark"), rs.getInt("status"), orderItems));
        }
        return orders;
    }

    public static ArrayList<Order> queryLike(Connection con, String queryText, boolean isLike, int preNum) throws SQLException {
        int start = (preNum - 1) * 10;
        ArrayList<Order> orders = new ArrayList<>();
        String sql;
        if (isLike) {
            sql = "select * from shop.`order` " +
                    "where concat(id,login_name,address,linkman,phonenumber,amount,time,remark,status) like ? " +
                    "limit ?,10";
        } else {
            sql = "select * from shop.`order` " +
                    "where id=? " +
                    "limit ?,10";
        }
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, queryText);
        pst.setInt(2, start);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            List<OrderItem> orderItems = OrderItemDao.queryAllOrderItemById(con, rs.getInt("id"));
            orders.add(new Order(rs.getInt("id"), rs.getString("login_name"), rs.getString("linkman"), rs.getString("address"), rs.getString("phonenumber"), rs.getDouble("amount"), rs.getDate("time"), rs.getString("remark"), rs.getInt("status"), orderItems));
        }
        return orders;
    }


    public static Order queryOrderById(Connection con, int id) throws SQLException {
        Order order = null;
        String sql = "select * from shop.`order` where id=?";
        //String sql = "select * from shop.`order` join shop.order_item oi on `order`.id = oi.order_id";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            List<OrderItem> orderItems = OrderItemDao.queryAllOrderItemById(con, rs.getInt("id"));
            order = new Order(rs.getInt("id"), rs.getString("login_name"), rs.getString("linkman"), rs.getString("address"), rs.getString("phonenumber"), rs.getDouble("amount"), rs.getDate("time"), rs.getString("remark"), rs.getInt("status"), orderItems);
        }
        return order;
    }

    public static int delOrder(Connection con, int id) throws SQLException {
        String sql = "delete from shop.`order` where id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        return pst.executeUpdate();
    }

    public static int update(Connection con, Order order) throws SQLException {
        String sql = "update shop.`order` set   linkman=?, address=?, phonenumber=?, remark=?, status=? where id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, order.getLinkman());
        pst.setString(2, order.getAddress());
        pst.setString(3, order.getPhonenumber());
        pst.setString(4, order.getRemark());
        pst.setInt(5, order.getStatus());
        pst.setInt(6, order.getId());
        return pst.executeUpdate();
    }

    public static int queryMaxId(Connection con) throws SQLException {
        String sql = "SELECT max( id ) as max_id FROM shop.`order`";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        int max_id = -1;
        while (rs.next()) {
            max_id = Integer.parseInt(rs.getString("max_id"));
        }
        return max_id;
    }

    @Override
    public int updateTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        return BaseDao.super.updateTarget(con, sql, setSqls);
    }
}
