package wen.service;

import wen.dao.goodsDao;
import wen.pojo.Goods;
import wen.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class testService {
    public static Connection con;

    static {
        try {
            con = JDBCUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        String sql = "INSERT INTO `order` (login_name,linkman,address,phonenumber,amount,time,remark,status) VALUES ( ?, ?, ?, ?,?, ?,?,?)";
       /* Date date = new Date();
        Order target = new Order(-1, "long", "文海龙", "南宁", 1010, 20.00, date , "无", 1);
        goodsDao goodsDao = new goodsDao();*/
        Goods goods = new Goods();
        goodsDao goodsDao = new goodsDao();
    }

}
