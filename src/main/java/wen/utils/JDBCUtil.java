package wen.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
    /*static String url = "jdbc:mysql://localhost:3306/shop?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    static String userName = "root";
    static String passWord = "123456";*/
    //使用Druid连接池
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, passWord);*/
        Connection connection = DruidUtil.getConnection();
        return connection;
    }
}
