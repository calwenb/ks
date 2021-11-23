package wen.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis池子
 * 单例模式,只允许一个池子(懒汉)
 */
public class JedisPollUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPollUtil() {
    }

    /**
     * 双重加锁
     */
    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (JedisPollUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxTotal(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "localhost", 6379, 60000);
                }
            }
        }
        return jedisPool;
    }
}
