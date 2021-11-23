package wen.dao;

import wen.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * dao层 userDao
 * whl
 */
public class UserDao implements BaseDao {

    public static ArrayList<User> queryAllUsers(Connection con) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM `shop`.user";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("login_name"), rs.getString("pass_word"), rs.getInt("user_type"), rs.getString("phone_number"), rs.getString("email")));
        }
        return users;
    }

    public static User queryUserById(Connection con, int id) throws SQLException {
        User user = new User();
        String sql = "SELECT * FROM `shop`.user where id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("login_name"), rs.getString("pass_word"), rs.getInt("user_type"), rs.getString("phone_number"), rs.getString("email"));
        }
        return user;
    }

    public static User queryUserByLoginName(Connection con, String loginName) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM `shop`.user where login_name=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, loginName);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("login_name"), rs.getString("pass_word"), rs.getInt("user_type"), rs.getString("phone_number"), rs.getString("email"));
        }
        return user;
    }

    public static int addUser(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO `shop`.`user` ( `user_name`, login_name, pass_word, `user_type`, phone_number,`email` ) VALUES ( ?, ?, ?, ?, ?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.getUserName());
        pst.setString(2, user.getLoginName());
        pst.setString(3, user.getPassWord());
        pst.setInt(4, user.getUserType());
        pst.setString(5, user.getPhoneNumber());
        pst.setString(6, user.getEmail());
        return pst.executeUpdate();
    }

    public static int updateUser(Connection con, User user) throws SQLException {
        String sql = "update `shop`.`user` set  login_name=?, user_name=?,pass_word=?, `user_type`=?, phone_number=?,`email`=?  where id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.getLoginName());
        pst.setString(2, user.getUserName());
        pst.setString(3, user.getPassWord());
        pst.setInt(4, user.getUserType());
        pst.setString(5, user.getPhoneNumber());
        pst.setString(6, user.getEmail());
        pst.setInt(7, user.getId());
        return pst.executeUpdate();
    }


}
