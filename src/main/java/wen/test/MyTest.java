package wen.test;

import org.junit.Test;
import wen.dao.UserDao;
import wen.pojo.Order;
import wen.pojo.User;
import wen.service.OrderService;
import wen.utils.DruidUtil;
import wen.utils.JDBCUtil;
import wen.utils.MailUtil;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTest {

    @Test
    public void w2() throws SQLException {
        Connection connection = DruidUtil.getConnection();
        System.out.println(connection);
    }

    @Test
    public void w1() throws SQLException, ClassNotFoundException {
        System.out.println("=======================");
        Connection con = JDBCUtil.getConnection();
        ArrayList<User> users = UserDao.queryAllUsers(con);
        users.forEach(System.out::println);
        //UserDao.addUser(con, new User(45, "whl", "long", "123", 2, 10010, "2241241@qq.com"));
    }
    @Test
    public void t2() throws Exception {
        System.out.println("=======================");
        System.out.println(OrderService.orderCount());
    }
    @Test
    public void t3() throws MessagingException, GeneralSecurityException, UnsupportedEncodingException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            MailUtil.sendMail("mr.wen66@qq.com",i+"s",i+"s",i+"s");
        }
    }
}
