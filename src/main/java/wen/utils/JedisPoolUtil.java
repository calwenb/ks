package wen.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis池子
 * 单例模式,只允许一个池子(懒汉)
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    /**
     * 双重加锁
     */
    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxTotal(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "112.74.78.182", 6379, 60000, "WHL123456");
                }
            }
        }
        return jedisPool;
    }
}
