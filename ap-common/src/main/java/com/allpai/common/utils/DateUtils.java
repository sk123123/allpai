package com.allpai.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 11:50
 * 日期处理
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间目录 */
    public final static String DATE_DIR = "yyyyMMddHHmmss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatDetail(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DATE_DIR);
        return df.format(date);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date parse(String date) throws ParseException{
        DateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
        return format.parse(date);
    }

    public static Date parseDay(String date) throws ParseException{
        DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        return format.parse(date);
    }

    /**
     * 日期相减得到的分钟数
     * @param date1
     * @param date2
     * @return
     */
    public static int miBetween(Date date1, Date date2){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
