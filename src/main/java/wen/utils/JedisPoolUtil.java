package wen.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedis池子
 * 单例模式,只允许一个池子(懒汉)
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    /*static {
        InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(Integer.parseInt(pro.getProperty("minIdle")));
        poolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        poolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        poolConfig.setMaxWaitMillis(Integer.parseInt(pro.getProperty("maxWaitMillis")));
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig, "112.74.78.182", 6379, 60000, "WHL123456");
    }*/

    /**
     * 双重加锁
     */
    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("jedis.properties");
                    Properties pro = new Properties();
                    try {
                        pro.load(is);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMinIdle(Integer.parseInt(pro.getProperty("minIdle")));
                    poolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
                    poolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
                    poolConfig.setMaxWaitMillis(Integer.parseInt(pro.getProperty("maxWaitMillis")));
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(
                            poolConfig,
                            pro.getProperty("host"),
                            Integer.parseInt(pro.getProperty("port")),
                            Integer.parseInt(pro.getProperty("timeout")),
                            pro.getProperty("password"));
                }
            }
        }
        return jedisPool;
    }
}
