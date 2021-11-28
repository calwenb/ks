package wen.test;

import org.junit.Test;
import wen.pojo.Order;
import wen.pojo.User;
import wen.utils.JDBCUtil;
import wen.utils.MysqlFieldUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class BaseDaoTest {
    static Connection con;

    static {
        try {
            try {
                con = JDBCUtil.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 查测试
     * 1.写sql
     * 2.给问号设置
     * 3.填参数
     *
     * @throws Exception
     */
    @Test
    public void queryAll() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String sql = "SELECT * FROM `shop`.`user`";
        Object[] setSqls = {};
        ArrayList<Object> targets = queryAllTargets(con, sql, User.class, setSqls);
        System.out.println(targets.toString());
    }

    @Test
    public void queryOne() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String sql = "SELECT * FROM `shop`.user where id=?";
        Object[] setSqls = {"3"};
        User pojo = (User) queryTarget(con, sql, User.class, setSqls);
        System.out.println(pojo.toString());
    }

    @Test
    public void delete() throws SQLException {
        String sql = "DELETE FROM shop.`order` WHERE id =?";
        Object[] setSqls = {"9"};
        int i = deleteTarget(con, sql, setSqls);
        System.out.println(i);
    }

    @Test
    public void update() throws SQLException {
        String sql = "UPDATE `shop`.order  SET status=? WHERE id=?";
        Order order = new Order(6, "long", "文海龙", "南宁", "1010", 20.00, new Date(123123131), "无", 1);
        Object[] setSqls = {"2", order.getId()};
        int i = updateTarget(con, sql, setSqls);
        System.out.println(i);
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        String sql = "INSERT INTO `order` (login_name,linkman,address,phonenumber,amount,time,remark,status) VALUES ( ?, ?, ?, ?,?, ?,?,?)";
        Date date = new Date();
        Order target = new Order(-1, "whlwhlwhl", "文海龙", "南宁", "1010", 20.00, date, "无", 1);
        int i = addTarget(con, sql, target);
    }


    public static ArrayList<Object> queryAllTargets(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<Object> AllTargets = new ArrayList<>();
        Field[] fields = targetClass.getDeclaredFields();
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        Class<?>[] classes = new Class[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            classes[i] = fields[i].getType();
        }
        Constructor ClassCon = targetClass.getDeclaredConstructor(classes);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Object[] fieldsVal = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                fieldsVal[i] = rs.getObject(MysqlFieldUtil.fieldJavaToSql(fields[i].getName()));
            }
            Object target = ClassCon.newInstance(fieldsVal);
            AllTargets.add(target);
        }
        return AllTargets;
    }

    public static Object queryTarget(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object target = null;
        Field[] fields = targetClass.getDeclaredFields();
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        Class<?>[] classes = new Class[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            classes[i] = fields[i].getType();
        }
        Constructor ClassCon = targetClass.getDeclaredConstructor(classes);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Object[] fieldsVal = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                fieldsVal[i] = rs.getObject(MysqlFieldUtil.fieldJavaToSql(fields[i].getName()));
            }
            target = ClassCon.newInstance(fieldsVal);
        }
        return target;
    }

    public static int addTarget(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].getType().equals(List.class) || fields[i].getType().equals(Map.class))
                continue;
            pst.setObject(i, fields[i].get(target));
        }
        return pst.executeUpdate();
    }

    public static int deleteTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        return pst.executeUpdate();
    }

    public static int updateTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        return pst.executeUpdate();
    }

}
