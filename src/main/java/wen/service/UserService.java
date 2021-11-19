package wen.service;

import wen.dao.UserDao;
import wen.pojo.User;
import wen.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    /**
     * 验证登录,返回key:是否通过 , value:user信息
     */
    public static Map<Boolean, User> login(String loginName, String password) throws SQLException, ClassNotFoundException {
        HashMap<Boolean, User> msg = new HashMap<>();
        msg.put(false, null);
        Connection con = JDBCUtil.getConnection();
        ArrayList<User> users = UserDao.queryAllUsers(con);
        ArrayList loginNames = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++) {
            loginNames.add(users.get(i).getLoginName());
        }
        if (!loginName.isEmpty() && !password.isEmpty()) {
            User user = UserDao.queryUserByLoginName(con, loginName);
            if (user != null) {
                String passWord = user.getPassWord();
                if (loginNames.contains(loginName) && password.equals(passWord)) {
                    msg.put(true, user);
                }
            }
        }
        return msg;
    }

    public static User queryUserByLName(String LoginName) throws SQLException, ClassNotFoundException {
        Connection con=JDBCUtil.getConnection();
        User user = UserDao.queryUserByLoginName(con,LoginName);
        return user;
    }

    public static int addUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return UserDao.addUser(con, user);
    }
    public static int updateUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = JDBCUtil.getConnection();
        return UserDao.updateUser(con, user);
    }
}
