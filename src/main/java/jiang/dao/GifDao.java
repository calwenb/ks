package jiang.dao;

import wen.dao.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class GifDao implements BaseDao {
    @Override
    public ArrayList<Object> queryAllTargets(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return BaseDao.super.queryAllTargets(con, sql, targetClass, setSqls);
    }

    @Override
    public Object queryTarget(Connection con, String sql, Class targetClass, Object[] setSqls) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return BaseDao.super.queryTarget(con, sql, targetClass, setSqls);
    }

    /**
     * 操作需要自增的表
     *
     * @param con
     * @param sql
     * @param target
     * @return
     */
    @Override
    public int addTarget(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
        return BaseDao.super.addTarget(con, sql, target);
    }

    /**
     * 操作Id不自增
     *
     * @param con
     * @param sql
     * @param target
     */
    @Override
    public int addTarget2(Connection con, String sql, Object target) throws SQLException, IllegalArgumentException, IllegalAccessException {
        return BaseDao.super.addTarget2(con, sql, target);
    }

    @Override
    public int deleteTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        return BaseDao.super.deleteTarget(con, sql, setSqls);
    }

    @Override
    public int updateTarget(Connection con, String sql, Object[] setSqls) throws SQLException {
        return BaseDao.super.updateTarget(con, sql, setSqls);
    }
}
