
package wen.dao;


import wen.utils.MysqlFieldUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dao层基础接口,实现简单的增删改查
 * 建议其他Dao实现该接口
 * 通过反射机制动态执行
 * <p>
 * whl
 */
public interface BaseDao {
    default ArrayList<Object> queryAllTargets(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
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

    default Object queryTarget(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
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

    /**
     * 操作需要自增的表,基于反射
     *
     * @param con
     * @param sql
     * @param target
     * @return
     */
    default int addTarget(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
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


    /**
     * 操作Id不自增
     *
     * @param con
     * @param sql
     * @param target
     */
    default int addTarget2(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < fields.length; i++) { //从零开始
            fields[i].setAccessible(true);
            if (fields[i].getType().equals(List.class) || fields[i].getType().equals(Map.class))
                continue;
            pst.setObject(i + 1, fields[i].get(target));
        }

        return pst.executeUpdate();
    }

    default int deleteTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        return pst.executeUpdate();
    }

    default int updateTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < setSqls.length; i++) {
            pst.setObject(i + 1, setSqls[i]);
        }
        return pst.executeUpdate();
    }
}

