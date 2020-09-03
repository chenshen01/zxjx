package com.zxjx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * description
 *
 * @author liuzhixiang 2020/04/14 19:33
 */
public class DateUtil {
    public static final String SIMPLE_DATE = "yyyy-MM-dd";

    /**
     * <p>
     * 获取当前的时间格式为 yyyy-MM-dd HH:mm:ss格式
     * </p>
     *
     * @author liuzhixiang 2020/04/14 19:36
     */
    public static String getCurrentDateTamp(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * <p>
     *获取 YYYY-MM-DD格式时间
     * </p>
     *
     * @author liuzhixiang 2020/05/04 13:49
     */
    public static Date getCurrentDate() {
        return toSimpleDate(new Date());
    }

    public static Date getSimpleDate(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE);
        Date simpleDate;
        try {
            simpleDate = simpleDateFormat.parse(dateStr);
        } catch (ParseException p) {
            throw new RuntimeException("日期解析错误");
        }
        return toSimpleDate(simpleDate);
    }
    public static Date toSimpleDate(Date dateTime) {
        return java.sql.Date.valueOf(asLocalDate(dateTime));
    }
    private static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
