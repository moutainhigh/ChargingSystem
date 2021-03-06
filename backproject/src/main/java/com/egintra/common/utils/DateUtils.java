package com.egintra.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DATE工具类
 *
 * @author liushihao
 * @date 2021/6/21
 */
public class DateUtils {

    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_STRING = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_STRING_SPACE = "yyyyMMdd HH:mm:ss";
    public static final String DATE_FORMAT_YYMMDDHHMMSS = "yyMMddHHmmss";
    public static final String DATE_FORMAT_YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";

    public static final String DATE_FORMAT_SHOT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYY = "yyyy";
    public static final String DATE_FORMAT_MM = "MM";
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    public static final String DATE_FORMAT_8 = "yyyyMMdd";

    public static final String TIME_FORMAT_SHOT = "HH:mm:ss";
    public static final String TIME_FORMAT_HH = "HH";
    public static final String TIME_FORMAT_DD = "dd";
    public static final String TIME_IRREGULAR = "HHmmss";

    public static final String TIME_FORMAT_8 = "yyMMdd";
    public static final String DATE_FORMAT_7 = "yyyy/MM/dd";

    public static final Integer SECOND_IN_24_H = 86400;

    private DateUtils() {
        throw new IllegalAccessError("Utility class should't be instantiated.");
    }

    /**
     * 获取当前日期时间
     *
     * @return 当前日期时间
     */
    public static Date getCurrentDate() {
        DateTime dateTime = DateTime.now();
        return dateTime.toDate();
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期 yyyyMMdd
     */
    public static String getDateShort() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), DATE_FORMAT_8);
    }

    /**
     * 获取当前年
     *
     * @return 当前年 yyyy
     */
    public static String getCurYear() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), DATE_FORMAT_YYYY);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期 yyMMdd
     */
    public static String getDateUt() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), TIME_FORMAT_8);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期 yyyy-MM-dd
     */
    public static Date getCurDateString() {
        DateTime dateTime = DateTime.now();
        return parseDate(formatDate(dateTime.toDate(), DATE_FORMAT_SHOT), DATE_FORMAT_SHOT);
    }

    /**
     * 获取当前月
     *
     * @return 当前年 MM
     */
    public static String getCurMonth() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), DATE_FORMAT_MM);
    }

    /**
     * 获取当前日
     *
     * @return 当前日 dd
     */
    public static String getCurDay() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), TIME_FORMAT_DD);
    }

    /**
     * 获取当前时
     *
     * @return 当前时 HH
     */
    public static String getCurHours() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), TIME_FORMAT_HH);
    }

    /**
     * 获取当前日期时间
     *
     * @return 当前日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDate() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), DATE_FORMAT_LONG);
    }

    /**
     * 获取当前日期时间
     *
     * @return 当前日期时间 yyyyMMdd HH:mm:ss
     */
    public static String getCurDateSpace() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), DATE_FORMAT_STRING_SPACE);
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间  HHmmss
     */
    public static String getCurTime() {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), TIME_IRREGULAR);
    }

    /**
     * 获取当前日期时间
     *
     * @return 当前日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDate(String format) {
        DateTime dateTime = DateTime.now();
        return formatDate(dateTime.toDate(), format);
    }

    /**
     * 字符串转日期时间
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return 时间
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DATE_FORMAT_LONG);
    }

    /**
     * 字符串转日期时间
     *
     * @param dateStr yyyy-MM-dd
     * @return 时间
     */
    public static Date parseDates(String dateStr) {
        return parseDate(dateStr, DATE_FORMAT_SHOT);
    }


    /**
     * 字符串转指定格式日期时间
     *
     * @param dateStr    日期时间字符串
     * @param dateFormat 日期时间格式
     * @return 日期时间
     */
    public static Date parseDate(String dateStr, String dateFormat) {
        if (TIME_IRREGULAR.equals(dateFormat)) {
            dateStr = dateStr.substring(0, 2) + ":" + dateStr.substring(2, 4) + ":" + dateStr.substring(4, 6);
            dateFormat = TIME_FORMAT_SHOT;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
        DateTime dateTime = format.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    /**
     * 日期时间转字符串
     *
     * @param date 日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT_LONG);
    }

    /**
     * 日期时间转字符串
     *
     * @param date 日期时间
     * @return yyyy-MM-dd
     */
    public static String formatDates(Date date) {
        return formatDate(date, DATE_FORMAT_SHOT);
    }

    /**
     * 日期时间转指定格式字符串
     *
     * @param date       日期
     * @param dateFormat 格式
     * @return 日期时间字符串
     */
    public static String formatDate(Date date, String dateFormat) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(dateFormat);
    }

    /**
     * 时间计算，加秒
     *
     * @param date   时间
     * @param second 加多少秒
     * @return 相加后时间
     */
    public static Date addSecond(Date date, int second) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusSeconds(second);
        return d.toDate();
    }

    /**
     * 时间计算，减秒
     *
     * @param date   时间
     * @param second 减多少秒
     * @return 相减后时间
     */
    public static Date minusSeconds(Date date, int second) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.minusSeconds(second);
        return d.toDate();
    }

    /**
     * 时间计算，加分
     *
     * @param date    时间
     * @param minutes 加多少分
     * @return 相加后时间
     */
    public static Date addMiniter(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusMinutes(minutes);
        return d.toDate();
    }

    /**
     * 时间计算，减分
     *
     * @param date    时间
     * @param minutes 减多少分
     * @return 相减后时间
     */
    public static Date minusMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.minusMinutes(minutes);
        return d.toDate();
    }

    /**
     * 时间计算，加时
     *
     * @param date  时间
     * @param hours 加多少时
     * @return 相加后时间
     */
    public static Date plusHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusHours(hours);
        return d.toDate();
    }

    /**
     * 时间计算，减时
     *
     * @param date  时间
     * @param hours 减多少时
     * @return 相减后时间
     */
    public static Date minusHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.minusHours(hours);
        return d.toDate();
    }

    /**
     * 时间计算，加天
     *
     * @param date 时间
     * @param days 加多少天
     * @return 相加后时间
     */
    public static Date addDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusDays(days);
        return d.toDate();
    }

    /**
     * 时间计算，减天
     *
     * @param date 时间
     * @param days 减多少天
     * @return 相加后时间
     */
    public static Date minusDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.minusDays(days);
        return d.toDate();
    }

    /**
     * 时间计算，加年
     *
     * @param date  时间
     * @param years 加多少年
     * @return 相加后时间
     */
    public static Date addYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusYears(years);
        return d.toDate();
    }

    /**
     * 时间计算，加月
     *
     * @param date  时间
     * @param month 加多少月
     * @return 相加后时间
     */
    public static Date plusMonths(Date date, int month) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.plusMonths(month);
        return d.toDate();
    }

    /**
     * 时间计算，减年
     *
     * @param date  时间
     * @param years 减多少年
     * @return 相减后时间
     */
    public static Date minusYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        DateTime d = dateTime.minusYears(years);
        return d.toDate();
    }

    /**
     * 日期时间转时间字符串
     *
     * @param date 日期时间
     * @return HH:mm:ss
     */
    public static String getTime(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(TIME_FORMAT_SHOT);
    }

    /**
     * 获取星期几
     *
     * @param date 日期
     * @return 星期几
     */
    public static String getWeekOfDateStr(Date date) {
        // 返回值
        String res = "";
        DateTime dateTime = DateTimeFormat.forPattern(DATE_FORMAT_LONG).parseDateTime(formatDate(date));
        // Get the day of week field value.
        int dayOfWeek = dateTime.getDayOfWeek();

        switch (dayOfWeek) {
            case 1:
                res = "周一";
                break;
            case 2:
                res = "周二";
                break;
            case 3:
                res = "周三";
                break;
            case 4:
                res = "周四";
                break;
            case 5:
                res = "周五";
                break;
            case 6:
                res = "周六";
                break;
            case 7:
                res = "周日";
                break;

            default:
                break;
        }
        return res;
    }

    /**
     * 指定毫秒数表示的日期
     *
     * @param millis 毫秒数
     * @return 指定毫秒数表示的日期
     */
    public static Date getDate(long millis) {
        return new Date(millis);
    }

    /**
     * 指定日期的毫秒数
     *
     * @param date 指定日期
     * @return 指定日期的毫秒数
     */
    public static long getMillis(Date date) {
        return date.getTime();
    }

    /**
     * 指定时间戳的毫秒数
     *
     * @param ts 指定时间戳
     * @return 指定时间戳的毫秒数
     */
    public static long getMillis(Timestamp ts) {
        return ts.getTime();
    }

    /**
     * 获取指定时间
     *
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPointTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        return dt.toString(DATE_FORMAT_LONG);
    }

    /**
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @param format  自定义格式
     * @return parrten
     */
    public static String getPointTimePattern(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds, String format) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        return dt.toString(format);
    }

    /**
     * 获取上一月的最后一天
     *
     * @return 返回格式yyy-MM-dd
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //设置为该月第一天
        calendar.set(Calendar.DATE, 1);
        //再减一天即为上个月最后一天
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取前一天
     *
     * @return 返回格式yyy-MM-dd
     */
    public static String getPreDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_SHOT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return df.format(calendar.getTime());
    }

    /**
     * 判断查询日期与当前日期是否在同一月
     *
     * @param queryDate 查询日期
     * @return 判断是否为当月
     */
    public static boolean isTimeInMonth(Date queryDate) {
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(queryDate);
        nowCalendar.setTime(nowDate);
        //设置为该月第一天
        startCalendar.set(Calendar.DATE, 1);
        nowCalendar.set(Calendar.DATE, 1);
        Date monthStartDate = startCalendar.getTime();
        return DateUtils.formatDate(monthStartDate, DateUtils.DATE_FORMAT_SHOT)
                .equals(DateUtils.formatDate(nowCalendar.getTime(), DateUtils.DATE_FORMAT_SHOT));
    }

    /**
     * 获取指定日期月初
     *
     * @param queryDate
     * @return
     */
    public static Date getMonthFirstDay(Date queryDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(queryDate);
        startCalendar.set(Calendar.DATE, 1);
        return startCalendar.getTime();
    }

    /**
     * 获取指定日期月末
     *
     * @param queryDate
     * @return
     */
    public static Date getMonthEndDay(Date queryDate) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(queryDate);
        endCalendar.add(Calendar.MONTH, 1);
        endCalendar.set(Calendar.DATE, 1);
        endCalendar.add(Calendar.DATE, -1);
        return endCalendar.getTime();
    }
}
