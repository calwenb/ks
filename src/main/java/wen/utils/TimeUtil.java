package wen.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单利模式
 */
public class TimeUtil {

    private static TimeUtil timeUtil = null;

    private TimeUtil() {
    }

    public static TimeUtil getTimeUtil() {
        return timeUtil = new TimeUtil();
    }

    public  String formatTime(Date time ) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }
}
