package wen.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    static Jedis jedis;
    static {
        /*jedis= new Jedis("112.74.78.182", 6379);
        jedis.auth("WHL123456");*/
        jedis=new Jedis("localhost",6379);
    }
    public static Jedis getJedis() {
        return jedis;
    }
    public static  void closeJedis(){
        jedis.close();
    }
}
