package com.fms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 时间转换工具类
 */
public class DateUtil {
    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Calendar getChinaDateFormat(String dateStr) {
        if (dateStr != null && dateStr.length() > 0) {
            String[] newStr = dateStr.split("-");
            // 得到年
            Integer year = Integer.valueOf(newStr[0]);
            // 得到月
            Integer month = Integer.valueOf(newStr[1]) - 1;
            // 得到日
            Integer day = Integer.valueOf(newStr[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            return calendar;
        }
        return null;
    }

    /**
     * <p>
     * 返回日期（格式 yyyy年MM月dd日HH时mm分 ）
     * </p>
     *
     * @param timeMillis
     * @return
     */
    public static String formatDate(long timeMillis) {
        return formatDate(timeMillis, "yyyy年MM月dd日HH时mm分");
    }


    /**
     * <p>
     * 返回日期（格式 yyyy年MM月dd日HH时mm分 ）
     * </p>
     *
     * @param timeMillis
     * @param pattern
     * @return
     */
    public static String formatDate(Long timeMillis, String pattern) {
        if (timeMillis == null || timeMillis < 0) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timeMillis));
    }

}

