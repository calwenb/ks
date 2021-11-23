package wen.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import wen.utils.JedisPollUtil;
import wen.utils.KillTimeUtil;

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
        if (killTime==null||!now.after(killTime)) {
            return "还没到秒杀时间,请等待!";
        }
        if (uid.isEmpty() || gid.isEmpty()) {
            return "请求值不正确";
        }
        JedisPool jedisPool = JedisPollUtil.getJedisPool();
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
            /*if ((rs == null || rs.size() == 0)){
                System.out.println("执行事务失败,开始自旋");
                System.out.println("第"+(++count));
            }*/
        } while (rs == null || rs.size() == 0);
        //事务失败后,自旋
        //System.out.println("还有库存:" + jedis.get(GKey));
        jedis.close();
        return uid + "操作成功";
    }

    /**
     * 设置秒杀任务
     */
    public static String setKill(String gid, String killNum) {
        if (killNum.isEmpty() || gid.isEmpty()) {
            return "请求值不正确";
        }
        //1.判断是否有这个商品,并且库存是否足够
        Jedis jedis = JedisPollUtil.getJedisPool().getResource();
        String GKey = "ks:" + gid + ":goods";

        jedis.set(GKey, killNum);
        jedis.sadd(skgKey, gid);
        //System.out.println(GKey + " " + jedis.get(GKey));
        jedis.close();
        return "设置成功";
    }

    public static List querySKGoods() {
        ArrayList<Object> rs = new ArrayList<>();
        Jedis jedis = JedisPollUtil.getJedisPool().getResource();
        jedis.close();
        Set<String> smembers = jedis.smembers(skgKey);
        ArrayList<String> skGoodsID = new ArrayList<>(smembers);
        for (String id : skGoodsID) {
            rs.add(id);
        }
        return rs;
    }

    public static String clearRedis() {
        Jedis jedis = null;
        try {
            jedis = JedisPollUtil.getJedisPool().getResource();
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
