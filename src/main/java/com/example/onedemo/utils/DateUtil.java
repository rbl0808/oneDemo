package com.example.onedemo.utils;

/**
 * @author zxd
 * @Date 2022/10/10 09:23
 */
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取时间段内日期
     * @param startTime
     * @param endTime
     * @param type  1.日 2.周 3.月 4.半年 5.年
     * @return
     */
    public static List<String> getAllDay(String startTime, String endTime, String type){
        List<String> res =new ArrayList<>();
        if(StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime) || StringUtils.isBlank(type)){
            return res;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        try {
            startDate.setTime(dateFormat.parse(startTime));
            endDate.setTime(dateFormat.parse(endTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Calendar> allDates = new ArrayList<>();
        List<Calendar> allWeeks = new ArrayList<>();
        List<Calendar> allMonths = new ArrayList<>();

        Calendar currentDate = (Calendar) startDate.clone();

        while (!currentDate.after(endDate)) {
            allDates.add((Calendar) currentDate.clone());

            if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                allWeeks.add((Calendar) currentDate.clone());
            }

            if (currentDate.get(Calendar.DAY_OF_MONTH) == 1) {
                allMonths.add((Calendar) currentDate.clone());
            }

            currentDate.add(Calendar.DATE, 1);
        }

        if(StringUtils.equals(type,"1")){
            res.addAll(allDates.stream().map(date -> dateFormat.format(date.getTime())).collect(Collectors.toList()));
        }else if(StringUtils.equals(type,"2")){
            res.addAll(allWeeks.stream().map(date -> dateFormat.format(date.getTime())).collect(Collectors.toList()));
        }else if(StringUtils.equals(type,"3")){
            res.addAll(allMonths.stream().map(date -> dateFormat.format(date.getTime())).collect(Collectors.toList()));
        }
        return res;
    }

    /**
     * 获取日期在第几周
     * @param time yyyyMMdd格式
     * @return
     */
    public static String getWeek(String time){
        if(StringUtils.isBlank(time)){
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date;
        String week = "";
        try {
            date = dateFormat.parse(time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // 获取日期是今年的第几周
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

            // 获取日期是第几个月（注意，月份从0开始，所以需要加1）
            int month = calendar.get(Calendar.MONTH) + 1;

            week = weekOfYear + "";
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return week;
    }
}

