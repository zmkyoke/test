package com.proper.phip.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtils {

    /**
     * 相差日期天
     * @param date 日期
     * @param differNum 整数表示后n天，负数表示前n天
     * @param format 日期格式
     * @return 日期字符串
     */
    public static String differDayFormat(Date date, int differNum, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, differNum);
        return df.format(calendar.getTime());
    }

    /**
     * 相差日期月
     * @param date 日期
     * @param differNum 整数表示后n月，负数表示前n月
     * @param format 日期格式
     * @return 日期字符串
     */
    public static String differMonthFormat(Date date, int differNum, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, differNum);
        return df.format(calendar.getTime());
    }

    /**
     * 相差日期年
     * @param date 日期
     * @param differNum 整数表示后n月，负数表示前n月
     * @param format 日期格式
     * @return 日期字符串
     */
    public static String differYearFormat(Date date, int differNum, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, differNum);
        return df.format(calendar.getTime());
    }
}
