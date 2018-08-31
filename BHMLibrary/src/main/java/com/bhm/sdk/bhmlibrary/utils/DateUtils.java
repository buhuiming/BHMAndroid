package com.bhm.sdk.bhmlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bhm on 2018/8/30.
 */

public class DateUtils {

    /** 获取年份
     * @return
     */
    public static String getYear(){
        Calendar calendar= Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /** 获取月份
     * @return
     */
    public static String getMonth(){
        Calendar calendar= Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.MONTH)+1);
    }

    /** 获取月份
     * @return
     */
    public static String getMonthFormat(){
        Calendar calendar= Calendar.getInstance();
        if(calendar.get(Calendar.MONTH) + 1 < 10){
            return "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }
        return String.valueOf(calendar.get(Calendar.MONTH)+1);
    }

    /** 获取日
     * @return
     */
    public static String getDay(){
        Calendar calendar= Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /** 获取小时
     * @return
     */
    public static String getHours(){
        Calendar calendar= Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /** 获取分
     * @return
     */
    public static String getMinute(){
        Calendar calendar= Calendar.getInstance();
        return calendar.get(Calendar.MINUTE) < 10 ? "0" + calendar.get(Calendar.MINUTE)
                : String.valueOf(calendar.get(Calendar.MINUTE));
    }

    /**
     * 获取日期中的年
     * @param date 日期
     * @return 年份
     */
    public static String getYear(Date date){
        SimpleDateFormat f_year = new SimpleDateFormat("yyyy", Locale.CHINA);
        return f_year.format(date);
    }

    /**
     * 获取日期中的月
     * @param date 日期
     * @return 月份
     */
    public static String getMonth(Date date){
        SimpleDateFormat f_month=new SimpleDateFormat("MM", Locale.CHINA);
        return f_month.format(date);
    }

    /**
     * 获取日期中天
     * @param date 日期
     * @return 天
     */
    public static String getDay(Date date){
        SimpleDateFormat f_day=new SimpleDateFormat("dd", Locale.CHINA);
        return f_day.format(date);
    }

    /**
     * 获取日期中时
     * @param date 日期
     * @return 天
     */
    public static String getHour(Date date){
        SimpleDateFormat f_day=new SimpleDateFormat("HH", Locale.CHINA);
        return f_day.format(date);
    }

    /**
     * 获取日期中分
     * @param date 日期
     * @return 天
     */
    public static String getMinute(Date date){
        SimpleDateFormat f_day=new SimpleDateFormat("mm", Locale.CHINA);
        return f_day.format(date);
    }

    /**
     * 获取日期中秒
     * @param date 日期
     * @return 天
     */
    public static String getSecond(Date date){
        SimpleDateFormat f_day=new SimpleDateFormat("ss", Locale.CHINA);
        return f_day.format(date);
    }

    /**
     * @param inTime
     * @return
     */
    public static String transferFormat(String inTime){
        try{
            SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            return s2.format(s2.parse(s1.format(s1.parse(inTime))));
        }catch (Exception e) {
            try{
                SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                return s2.format(s2.parse(s1.format(s1.parse(inTime))));
            }catch (Exception e1){

            }
        }
        return inTime;
    }

    /** 改变时间格式，由2018-4-5 8:8 改成2018-04-05 08:08 或由2018.4.5 8:8 改成2018.04.05 08:08
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @param i4
     * @param isShiFen
     * @param fu
     * @return
     */
    public static String changeTime(int i,int i1, int i2, int i3,int i4,boolean isShiFen,String fu){
        String str1 = fu;
        String str2 = fu;
        String str3 = " ";
        String str4 = ":";
        if(i1 < 10){
            str1 = fu + "0";
        }
        if(i2 < 10){
            str2 = fu + "0";
        }
        if(i3 < 10){
            str3 = " 0";
        }
        if(i4 < 10){
            str4 = ":0";
        }
        if(isShiFen) {
            return i + str1 + i1 + str2 + i2 + str3 + i3 + str4 + i4;//返回 2018-04-01 05:02
        }else{
            return i + str1 + i1 + str2 + i2;//返回 2018-04-01
        }
    }

    /**
     * 字符串转日期，
     * @param dateStr
     * @param format 格式为："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date formatDate(String dateStr, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        Date result = null;
        try {
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 日期字符串转时间戳
     * @param dateStr
     * @param format 格式为："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static long transForMilliSecond(String dateStr, String format){
        Date date = formatDate(dateStr, format);
        return transForMilliSecond(date);
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public static long transForMilliSecond(Date date){
        if(date == null) return 0;
        return (date.getTime() / 1000);
    }

    /**
     * 日期字符串转时间戳
     * @param dateStr
     * @return
     */
    public static long transForMilliSecond(String dateStr){
        Date date = formatDate(dateStr);
        return transForMilliSecond(date);
    }

    /**
     * 字符串转日期，格式为："yyyy-MM-dd HH:mm:ss"
     * @param dateStr
     * @return
     */
    public static Date formatDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date result = null;
        try {
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
