package wen.utils;

import java.util.Date;

/**
 * 获得唯一的秒杀时间
 * 单例模式 (懒汉式)
 */
public class KillTimeUtil {
    private static Date killTime = null;

    private KillTimeUtil() {
    }

    public static Date getKillTime() {
        return killTime;
    }

    public static void setKillTime(Date time) {
        killTime = time;
    }
}
