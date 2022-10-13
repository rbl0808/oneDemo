package com.example.onedemo.utils;

/**
 * @author zxd
 * @Date 2022/10/10 09:23
 */
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
//    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
//    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
//    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
//    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
//    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    public static final String TIME_FORMATTER =  "HHmmss";
    public static final String YEAR_MONTH_FORMATTER =  "yyyy-MM";
    public static final String SHORT_DATE_FORMATTER =  "yyMMdd";
    public static final String DATE_FORMATTER =  "yyyy-MM-dd";
    public static final String SHORT_DATETIME_FORMATTER =  "yyMMddHHmmss";
    public static final String DATETIME_FORMATTER =  "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATETIME_FORMATTER =  "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 返回当前的日期
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }


    /**
     * 日期相隔秒
     */
    public static long periodHours(LocalDateTime startDateTime,LocalDateTime endDateTime){
        return Duration.between(startDateTime, endDateTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 日期相隔天数
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 日期相隔周数
     */
    public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 日期相隔月数
     */
    public static long periodMonths(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.MONTHS);
    }

    /**
     * 日期相隔年数
     */
    public static long periodYears(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.YEARS);
    }

    /**
     * 是否当天
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }
    /**
     * 获取当前毫秒数
     */
    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(LocalDate localDate){
        return localDate.isLeapYear();
    }

    /**
     * 转换时间戳为其他格式
     */
    public static String getTime(Long time,String pattern){
        return Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 转换string时间格式
     */
    public static String exchangeStringPattern(String timeStr,String pattern) {
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern)).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 年月日 时分秒
     */
    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
}

