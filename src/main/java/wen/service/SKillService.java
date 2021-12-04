package wen.service;

import pan.pojo.Goods;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import wen.dao.goodsDao;
import wen.utils.JDBCUtil;
import wen.utils.JedisPoolUtil;
import wen.utils.KillTimeUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SKillService {
    static String skgKey = "ks:goods";

    public static String doSKill(String uid, String gid) {
        Date now = new Date();
        Date killTime = KillTimeUtil.getKillTime();
        if (killTime == null || !now.after(killTime)) {
            return "还没到秒杀时间,请等待!";
        }
        if (uid.isEmpty() || gid.isEmpty()) {
            return "请求值不正确";
        }
        JedisPool jedisPool = JedisPoolUtil.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        String GKey = "ks:" + gid + ":goods";
        String UKey = "ks:" + gid + ":user";
        List<Object> rs;
        //事务失败后,自旋
        do {
            //乐观锁监视库存的key
            jedis.watch(GKey);
            String goodCount = String.valueOf(jedis.get(GKey));
            if ("null".equals(goodCount)) {
                jedis.close();
                return "还未开始";
            }
            if (jedis.sismember(UKey, uid)) {
                jedis.close();
                return "重复抢购";
            }
            if ((Integer.parseInt(goodCount) <= 0)) {
                jedis.close();
                return "库存不足";
            }
            //开始事务
            Transaction transaction = jedis.multi();
            transaction.decr(GKey);
            transaction.sadd(UKey, uid);
            rs = transaction.exec();

        } while (rs == null || rs.size() == 0);
        //事务失败后,自旋
        //System.out.println("还有库存:" + jedis.get(GKey));
        jedis.close();
        return uid + "操作成功";
    }

    /**
     * 设置秒杀任务
     */
    public static String setKill(String gid, String killNum) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (!checkHave(gid, killNum)) { //如果数据库没有则直接返回
            return "数据库中没有改商品id或者库存不够,请检查数据库!!!";
        }
        if (killNum.isEmpty() || gid.isEmpty()) {
            return "请求值不正确";
        }
        //1.判断是否有这个商品,并且库存是否足够
        Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
        String GKey = "ks:" + gid + ":goods";

        jedis.set(GKey, killNum);
        jedis.sadd(skgKey, gid);
        jedis.close();
        return "设置成功";
    }

    private static boolean checkHave(String gid, String killNum) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {//数据库是否有该商品
        goodsDao goodsDao = new goodsDao();
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM shop.goods WHERE id=? and pnum>=?;";
        Object[] setSqls = {gid, killNum};
        Object o = goodsDao.queryTarget(con, sql, Goods.class, setSqls);
        if (o != null) {
            return true;
        }
        return false;
    }

    public static List querySKGoods() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        Connection conn = JDBCUtil.getConnection();
        goodsDao goodsDao = new goodsDao();
        ArrayList<Goods> goodses = new ArrayList<>();
        Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
        Set<String> smembers = jedis.smembers(skgKey);
        jedis.close();
        ArrayList<String> skGoodsID = new ArrayList<>(smembers);
        String sql = "SELECT * FROM shop.`goods` WHERE id=?";
        for (String id : skGoodsID) {
            Object[] setSqls = {id};
            goodses.add((Goods) goodsDao.queryTarget(conn, sql, Goods.class, setSqls));
        }
        return goodses;
    }

    public static String clearRedis() {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedisPool().getResource();
            jedis.flushDB();
            return "清空成功!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "操作失败";
        } finally {
            jedis.close();
        }
    }

    public static String SetSKillTime(String killTime) {
        try {
            String sTime = killTime.replace('T', ' ');
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date time = format.parse(sTime);
            KillTimeUtil.setKillTime(time);
            return "设置秒杀时间成功";
        } catch (ParseException e) {
            e.printStackTrace();
            return "设置秒杀时间操作失败";
        }
    }
}
