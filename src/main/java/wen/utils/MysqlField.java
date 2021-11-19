package wen.utils;

import org.junit.Test;
import wen.pojo.Order;
import wen.pojo.User;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MysqlField {
    static Connection con;

    {
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

    @Test
    public void s() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String sql = "SELECT * FROM `shop`.`USER` LIMIT 1,2";
        Object[] sqlFields = {};
        for (int i = 0; i < 10000; i++) {
            ArrayList<Object> targets = queryAllTargets(con, sql, User.class, sqlFields);
            System.out.println(targets.toString());
        }
    }

    @Test
    public void a() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String sql = "SELECT * FROM `shop`.user where id=?";
        Object[] sqlFields = {"3"};
        User pojo = (User) queryTarget(con, sql, User.class, sqlFields);
        System.out.println(pojo.toString());
    }

    @Test
    public void b() throws SQLException {
        String sql = "DELETE FROM shop.`order` WHERE id =?";
        Object[] sqlFields2 = {"9"};
        int i = deleteTarget(con, sql, sqlFields2);
        System.out.println(i);
    }

    @Test
    public void c() throws SQLException {
        String sql = "UPDATE `shop`.order  SET status=? WHERE id=?";
        Order order = new Order(6, "long", "文海龙", "南宁", 1010, 20.00, new Date(123123131), "无", 1);
        Object[] sqlFields3 = {"2", order.getId()};
        int i = updateTarget(con, sql, sqlFields3);
        System.out.println(i);

    }

    @Test
    public void d() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        String sql = "INSERT INTO `order` (login_name,linkman,address,phonenumber,amount,time,remark,status) VALUES ( ?, ?, ?, ?,?, ?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date date = new Date();
        Order target = new Order(-1, "long", "文海龙", "南宁", 1010, 20.00, date, "无", 1);
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
                fieldsVal[i] = rs.getObject(MysqlField.fieldJavaToSql(fields[i].getName()));
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
                fieldsVal[i] = rs.getObject(MysqlField.fieldJavaToSql(fields[i].getName()));
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

    public static String fieldSqlToJava(String field) {
        StringBuffer sb = new StringBuffer(field);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '_') {
                sb.replace(i, i + 2, String.valueOf((char) (sb.charAt(i + 1) - 32)));
            }
        }
        return sb.toString();
    }

    public static String fieldJavaToSql(String field) {
        StringBuffer sb = new StringBuffer(field);
        for (int i = 0; i < sb.length(); i++) {
            if (Character.isUpperCase(sb.charAt(i))) {
                sb.replace(i, i + 1, "_" + (char) (sb.charAt(i) + 32));
            }
        }
        return sb.toString();
    }
}
