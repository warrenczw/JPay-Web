package com.czw.common.tools;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SimpleTimeZone;


/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月11日
 * www.cuizhiwei.com
 */
public class DateTools {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");// 数据库使用的日期格式
	public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
	public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD = "yyyyMMdd";
	public static final String YMDHM = "yyyyMMddHHmm";
	public static final String YMDHMS = "yyyyMMddHHmmss";
	public static final String ymd = "yyyy/MM/dd";
	public static final String ymd_HM = "yyyy/MM/dd HH:mm";
	public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * 两时间相减 返回
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getSubTwoTime(String endTime,String startTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
		try{
			
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			
			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
//	  System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
			if(hours <0){
				hours = new BigDecimal(hours).abs().intValue();
			}
			if(minutes <0){
				minutes = new BigDecimal(minutes).abs().intValue();
			}
			return ""+days+"-"+hours+"-"+minutes;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 两时间相减 返回
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getSubTwoTimeYY(String endTime,String startTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			return ""+days;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 两时间相减 返回
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getSubTwoTime1(String endTime,String startTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			
			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
//	  System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
			if(hours <0){
				hours = new BigDecimal(hours).abs().intValue();
			}
			if(minutes <0){
				minutes = new BigDecimal(minutes).abs().intValue();
			}
			return ""+days+"-"+hours+"-"+minutes;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 
	 * @param dateTime yyyy-MM-dd HH:mm:ss
	 * @return unix 时间
	 */
	public static String getUnixTimeStamp(String dateTime){
		
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dataTimeFormat.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (c.getTimeInMillis()/1000)+"";
	}
	/**
	 * unix 时间 转换
	 * @param timestampString  1252639886
	 * @param formats
	 * @return
	 */
	public static String gerUnixTime2String(String timestampString, String formats){   
		
		 if(Tools.stringIsNotNull(timestampString) || "null".equals(timestampString)){
			 return "";
		 }
		  Long timestamp = Long.parseLong(timestampString)*1000;    
		  String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
		  return date;    
	}  
	/**
	 * 
	 * @param dateTime yyyy-MM-dd HH:mm:ss
	 * @return unix 时间
	 */
	public static String getCurrentUnixTimeStamp(){
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return (c.getTimeInMillis()/1000)+"";
	}
	public static String formatDateTime(Date date) {
		return dataTimeFormat.format(date);
	}

	/**
	 * 创建一个"yyyy-MM-dd"日期的格式化对象
	 * @return "yyyy-MM-dd"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * 创建一个"yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 * @return "yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDHMSFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * "yyyyMMddHHmmss"格式的日期转换为"yyyy-MM-dd HH:mm:ss"格式的日期
	 * @param shortYMDHMS "yyyyMMddHHmmss"格式的日期
	 * @return "yyyy-MM-dd HH:mm:ss"格式的日期
	 * @throws ParseException
	 */
	public static String toLongYMDHMS(String shortYMDHMS) throws ParseException {
		return newLongYMDHMSFormat().format(newShortYMDHMSFormat().parse(shortYMDHMS));
	}

	/**
	 * 获得"yyyy-MM-dd"格式的当前日期
	 * @return 返回"yyyy-MM-dd"格式的当前日期
	 */
	public static String getLongYMD() {
		return newLongYMDFormat().format(new Date());
	}
	/**
	 * 2015年12月21日
	 * @return
	 */
	public static String getLongYMDChina() {
		String str = newLongYMDFormat().format(new Date());
		return str.split("-")[0]+"年"+str.split("-")[1]+"月"+str.split("-")[2]+"日";
	}

	/**
	 * 创建一个"yyyyMMdd"日期的格式化对象
	 * @return "yyyyMMdd"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	/**
	 * 创建一个"yyyyMMddHHmmss"日期的格式化对象
	 * @return "yyyyMMddHHmmss"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDHMSFormat() {
		return new SimpleDateFormat("yyyyMMddHHmmss");
	}

	/**
	 * 获得"yyyyMMddHHmmss"格式的当前日期
	 * @return 返回"yyyyMMddHHmmss"格式的当前时间
	 */
	public static String getShortYMDHMS() {
		return newShortYMDHMSFormat().format(new Date());
	}

	/**
	 * "yyyyMMdd"格式的日期转换为"yyyy-MM-dd"格式的日期
	 * @param shortYMD "yyyyMMdd"格式的日期
	 * @return "yyyy-MM-dd"格式的日期
	 * @throws ParseException
	 */
	public static String toLongYMD(String shortYMD) {
		try {
			return newLongYMDFormat().format(newShortYMDFormat().parse(shortYMD));
		} catch (ParseException e) {
			// Auto-generated catch block

			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 功能：生成日期yyyyMMdd
	 *
	 * @return
	 */
	public static String getDbDate() {
		return dateFormatDB.format(new Date());
	}

	/**
	 * 
	 * 功能：把日期yyyy-MM-dd格式转换成yyyyMMDD日期格式
	 *
	 * @param dateStr
	 * @return
	 */
	public static String convertClientDateToDbDate(String dateStr) {
		String dbDateStr = null;
		try {
			dbDateStr = dateFormatDB.format(dateFormat.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbDateStr;
	}

	/**
	 * 
	 * 功能：解析数据库中的日期字符串 格式:yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将UNIX时间转换成标准时间
	 * @param timestampString
	 * @return
	 */
	public static String getDate(String timestampString)
	{
		Long timestamp = Long.parseLong(timestampString)*1000;  
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));  
		return date;  
	}

	/**
	 * 
	 * 功能：格式化日期字符串
	 * 例如：2008-8-8  转换为2008-08-08
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrFormat(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 功能：格式化日期字符串
	 * 例如：2008-8  转换为2008-08
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrFormat2(String dateStr) {
		try {
			Date date = dateFormat2.parse(dateStr);
			return dateFormat2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 2008-8-8 转 20080808 
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrFormatyyyyMMdd(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormatDB.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能：解析数据库中的时间字符串 格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateTime(String dateTimeStr) {
		Date date = null;
		try {
			date = dataTimeFormat.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param startDate
	 *          开始时间
	 * @param endDate
	 *          结束时间
	 * @return
	 */
	public static int calcDays(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormat.parse(startDate).getTime();
			long end = dateFormat.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param startDate
	 *          开始时间
	 * @param endDate
	 *          结束时间
	 * @return
	 */
	public static int calcDay(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormatDB.parse(startDate).getTime();
			long end = dateFormatDB.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *          日期
	 * @param day
	 *          天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *          日期
	 * @param minute
	 *          分钟
	 * @return 返回相加后的日期
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minute) * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：添加指定秒杀的时间
	 *
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		long s = date.getTime();
		s = s + second * 1000;
		return new Date(s);
	}

	/**
	 * 功能：指定日期减去指定天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：分钟相差 minute的时间值
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateByMinuteAdd(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 功能:两个日期相隔天数
	 * 
	 * @param startDate
	 *          开始日期
	 * @param endDate
	 *          结束日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / (24 * 3600 * 1000);
		return (int) s;
	}

	/**
	 * 功能：传入时间按所需格式返回时间字符串
	 * 
	 * @param date
	 *          java.util.Date格式
	 * @param format
	 *          yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date == null) {
				date = new Date();// 如果时间为空,则默认为当前时间
			}
			if (Tools.stringIsNotNull(format)) {// 默认格式化形式
				format = "yyyy-MM-dd";
			}
			DateFormat df = new SimpleDateFormat(format);
			result = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 功能：传入时间字符串按所需格式返回时间
	 * 
	 * @param dateStr
	 *          时间字符串
	 * @param format
	 *          跟传入dateStr时间的格式必须一样 yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static Date format(String dateStr, String format) {
		if (Tools.stringIsNotNull(dateStr)) {
			return new Date();
		}
		if (Tools.stringIsNotNull(format)) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		try {
			DateFormat f = new SimpleDateFormat(format);
			date = f.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 功能：时间字符串格式转换
	 * 
	 * @param dateStr
	 *          时间字符串
	 * @param format
	 *          时间字符串的格式
	 * @param toFormat
	 *          格式为的格式
	 * @return
	 */
	public static String format(String dateStr, String format, String toFormat) {
		return format(format(dateStr, format), toFormat);
	}

	/**
	 * 功能：格式化rss的时间
	 * 输入:
	 * @param date
	 * @return
	 */
	public static String formatRssDate(Date date) {
		if (date == null) {
			date = new Date();// 如果时间为空,则默认为当前时间
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		SimpleTimeZone zone = new SimpleTimeZone(8, "GMT");
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}

	/**
	 * 功能：返回年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);

	}

	/**
	 * 功能：返回月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能：返回日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能：返回小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能：返回分
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 功能：返回星期 1：星期一，2:星期二 ... 6:星期六 7:星期日
	 * 
	 * @param date
	 * @return
	 */
	public static int getChinaWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			return 7;
		} else {
			return week;
		}
	}

	/**
	 * 功能：返回秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond2(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 功能：返回毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 功能：获取当前月的第一天日期
	 * 
	 * @return
	 */
	public static Date getMonFirstDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	/**
	 * 功能：获取当前月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonLastDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date), 1);
		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}
	/**
	 * 功能：获取上个月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonUpDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date)-1, 1);
		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}
	
	/** 获得本月的第一天的日期 */
	public static String getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-01";
		return s;
	}
	/** 获得当前月份2015-11 */
	public static String getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal));
		return getDateStrFormat2(s);
	}

	/** 获得本月的最后一天的日期 */
	public static String getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-" + getDays(cal);
		return s;
	}
	
	/** 获得给定日期当月的天数 */
	public static int getDays(Calendar cal) {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/** 获得给定日历的年 */
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}
	
	/** 获得给定日历的月 */
	public static int getMonth(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1);
	}

	/** 获得给定日期字符串对应的年 */
	public static int getYear(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.YEAR));
	}
	
	/** 日期转日历* */
	public static Calendar convertDateToCal(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/** 字符转换日历(动态格式转换) */
	public static Calendar convertStrToCal(String date_str, String type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStrToDate(date_str, type));
		return cal;
	}
	
	/** 字符转换日期(动态格式转换) */
	public static Date convertStrToDate(String date_str, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			return dateformat.parse(date_str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：获取当前日期 格式:2008-02-02 23:11:10
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		return dataTimeFormat.format(date);
	}

	/**
	 * 功能：获取当前日期 格式:20101010
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	/**
	 * 创建一个"yyyyMM"日期的格式化对象
	 * @return "yyyyMM"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMFormat() {
		return new SimpleDateFormat("yyyyMM");
	}

	/**
	 * 获得距离输入月份的diffMonth月的日期
	 * @param month "yyyyMM"格式的日期
	 * @param diffMonth 相差的月数
	 * @return "yyyyMM"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDiffMonth(String month, int diffMonth) {
		SimpleDateFormat sdf = newShortYMFormat();
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.MONTH, diffMonth);
		return sdf.format(c.getTime());
	}

	/**
	 * 获得距离给定日期diffDay天的日期
	 * @param shortYMD "yyyyMMdd"格式的日期
	 * @param diffDay 相差的天数
	 * @return "yyyyMMdd"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDDiffDay(String shortYMD, int diffDay) {
		SimpleDateFormat sdf = newShortYMDFormat();
		try {
			sdf.parse(shortYMD);
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.DATE, diffDay);
		return sdf.format(c.getTime());
	}
	/**
	 * 当前时间加 减days
	 * @param shortYMD
	 * @param diffDay
	 * @return
	 */
	public static String getAddDay(int diffDay) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, diffDay);
        return sf.format(c.getTime());
	}

	/**
	 * 获取某月份的最后一天
	 * @param shortYM 月份
	 * @return 输入月份的最后一天
	 * @throws Exception
	 */
	public static String getEndDayOfMonth(String shortYM) {
		String month = "";
		try {
			month = getShortYMDiffMonth(shortYM, 1);
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return getShortYMDDiffDay(month + "01", -1);
	}

	/**
	* 获得"yyyyMMdd"格式的当前日期
	* @return 返回"yyyyMMdd"格式的当前日期
	*/
	public static String getShortYMD() {
		return newShortYMDFormat().format(new Date());
	}

	/**
	 * 获得两个日期之间的所有日期列表（包括起始日期和结束日期）
	 * @param startShortYMD "yyyyMMdd"格式的起始日期
	 * @param endShortYMD "yyyyMMdd"格式的结束日期
	 * @return "yyyyMMdd"格式的日期列表
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getShortYMDList(String startShortYMD, String endShortYMD) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMDFormat();
		startDateFormat.parse(startShortYMD);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMDFormat();
		endDateFormat.parse(endShortYMD);
		Calendar endCal = endDateFormat.getCalendar();

		List dateList = new ArrayList();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DATE, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得两个日期之间的所有日期列表（包括起始日期和结束日期）
	 * @param startShortYM "yyyyMM"格式的起始日期
	 * @param endShortYM "yyyyMM"格式的结束日期
	 * @return "yyyyMM"格式的日期列表
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getShortYMList(String startShortYM, String endShortYM) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMFormat();
		startDateFormat.parse(startShortYM);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMFormat();
		endDateFormat.parse(endShortYM);
		Calendar endCal = endDateFormat.getCalendar();

		List dateList = new ArrayList();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.MONTH, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 验证输入的日期是否合法
	 * @param expDate
	 * @return
	 */
	public static boolean checkExpDate(String expDate) {
		int year = Integer.parseInt(expDate.substring(0, 4));
		int month = Integer.parseInt(expDate.substring(4, 6));
		int day = Integer.parseInt(expDate.substring(6, 8));
		if (month > 12 || month < 1) {
			return false;
		}

		int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (isLeapYear(year)) {
			monthLengths[2] = 29;
		} else {
			monthLengths[2] = 28;
		}

		int monthLength = monthLengths[month];
		if (day < 1 || day > monthLength) {
			return false;
		}
		return true;
	}

	/** 
	 * 是否是闰年 
	 * */
	private static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}

	/**
	 * 
	 * 方法用途: 结束时间（end）与start时间进行比较<br>
	 * 实现步骤: 如果相等返回0，如果end大于start返回1，如果end小于start返回-1<br>
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static int compareEndAndStart(String start, String end) throws Exception {
		long s = 0;
		if (8 == start.length()) {
			s = dateFormatDB.parse(start).getTime();
		} else if (10 == start.length()) {
			s = dateFormat.parse(start).getTime();
		} else {
			s = dataTimeFormat.parse(start).getTime();
		}
		long e = 0;
		if (8 == end.length()) {
			e = dateFormatDB.parse(end).getTime();
		} else if (10 == end.length()) {
			e = dateFormat.parse(end).getTime();
		} else {
			e = dataTimeFormat.parse(end).getTime();
		}
		if (e > s) {
			return 1;
		} else if (e < s) {
			return -1;
		}
		return 0;
	}

	/**
	 * 根据传过来的字符串型的date，转换成对应的日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		Date ret = null;
		if (date.length() == 10) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 16) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 19) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 13) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 7) {
			try {
				ret = new SimpleDateFormat("yyyy-MM").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * 获取8位随机数
	 * @return
	 */
	public static String getRandomNum(){
		Random r=new Random();
		int a = r.nextInt(99999999);
		String	cardNum = ""+a;
		int length = cardNum.length();
		if(length<8){
			for(int j=0; j<(8-length); j++){
				cardNum = "0"+cardNum;
			}
		}
		return cardNum;
	}
	
	/** 
	 * 智能转换日期 
	 * 
	 * @param text 
	 * @return 
	 */
	public static Date smartFormat(String text) {
		Date date = null;
		try {
			if (text == null || text.length() == 0) {
				date = null;
			} else if (text.length() == 10) {
				date = formatStringToDate(text, Y_M_D);
			} else if (text.length() == 13) {
				date = new Date(Long.parseLong(text));
			} else if (text.length() == 14) {
				date = formatStringToDate(text, YMDHMS);
			} else if (text.length() == 16) {
				date = formatStringToDate(text, Y_M_D_HM);
			} else if (text.length() == 19) {
				date = formatStringToDate(text, Y_M_D_HMS);
			} else {
				throw new IllegalArgumentException("日期长度不符合要求!");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("日期转换失败!");
		}
		return date;
	}
	
	/** 
	 * 把字符串格式化成日期 
	 * 
	 * @param argDateStr 
	 * @param argFormat 
	 * @return 
	 */
	public static Date formatStringToDate(String argDateStr, String argFormat) throws Exception {
		if (argDateStr == null || argDateStr.trim().length() < 1) {
			throw new Exception("参数[日期]不能为空!");
		}
		String strFormat = argFormat;
		if (Tools.stringIsNotNull(strFormat)) {
			strFormat = Y_M_D;
			if (argDateStr.length() > 16) {
				strFormat = Y_M_D_HMS;
			} else if (argDateStr.length() > 10) {
				strFormat = Y_M_D_HM;
			}
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(strFormat);
		// 严格模式
		sdfFormat.setLenient(false);
		try {
			return sdfFormat.parse(argDateStr);
		} catch (ParseException e) {
			throw new Exception(e);
		}
	}
	public static String getSSTimeStamp(){
		  Date d = new Date(); 
		  SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss:SS");
		  String strddd = sdf.format(d); 
		  return strddd;
	}
	
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate() {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
//        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    

//		public static void main(String[] args) throws Exception {
//			
//			System.out.println(getSSTimeStamp());
//		}
		
		/**
		 * 功能：返回上旬/中旬/下旬
		 * 1 ：上旬  2： 中旬  3： 下旬
		 * @param date
		 * @return
		 */
		public static int getEarlyMidLate(Date date) {
			int day=getDay(date);
			int earlyMidLate=0;
			if(1<=day && day<= 10){
				earlyMidLate=1;
			}
			if(11<=day && day<=20){
				earlyMidLate=2;
			}
			if(20<day){
				earlyMidLate=3;
			}
			return earlyMidLate;
		}
	    
	    /**
	     * 将日期转换成Julian日期，即为哪一年的第几天
	     * @param date
	     * @author gongz
	     * @return
	     */
	    public static int dateToJulian(Date date){
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        int year = calendar.get(Calendar.YEAR)%100;
	        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
	        return year*1000 + dayOfYear;
	    }
	    /**
	     * 将Julian日期转化为date，即为哪一年的第几天
	     * @param date
	     * @return
	     */
	    public static Date JulianToDate(int date){
	        int year = (date/1000)+2000;
	        int dayOfYear = date %1000;
	        Calendar calendar =Calendar.getInstance();
	        calendar.set(Calendar.YEAR, year);
	        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
	        return calendar.getTime();
	    }
	    
	    /**
	     * 返回当前月份的第一天
	     * @author gongz
	     * @return
	     */
	    public static Date currentMonthFirstDay(){
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MONTH, 0);
	        calendar.set(Calendar.DAY_OF_MONTH, 1); //设置为1号，当前日期既为本月第一天
	        return calendar.getTime();
	    }
	    
	    /**
	     * 返回当前月份的最后一天
	     * @author gongz
	     * @return
	     */
	    public static Date currentMonthLastDay(){
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MONTH, 0);
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	        return calendar.getTime();
	    }
	    public static int statisSubDay(Date endDate,Date startDate){
	    	
	    	  Calendar fromCalendar = Calendar.getInstance();   
              fromCalendar.setTime(startDate);   
              fromCalendar.set(Calendar.HOUR_OF_DAY, 0);   
              fromCalendar.set(Calendar.MINUTE, 0);   
              fromCalendar.set(Calendar.SECOND, 0);   
              fromCalendar.set(Calendar.MILLISECOND, 0);   
              
              Calendar toCalendar = Calendar.getInstance();   
              toCalendar.setTime(endDate);   
              toCalendar.set(Calendar.HOUR_OF_DAY, 0);   
              toCalendar.set(Calendar.MINUTE, 0);   
              toCalendar.set(Calendar.SECOND, 0);   
              toCalendar.set(Calendar.MILLISECOND, 0);   
              Long tempString = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
              return Integer.valueOf(tempString.toString());
	    }
	    
	    /**
		 * 时间减去 几小时 返回时间
		 * @param startTime
		 * @param endTime
		 * @return
		 */
		public static String getSubTwoDate(String strDate,int a){
			try{
				 SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				 Calendar   dar=Calendar.getInstance();    
				 dar.setTime(dft.parse(strDate));    
				 dar.add(java.util.Calendar.HOUR_OF_DAY, -a);     
				 return dft.format(dar.getTime());
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
		}
		
		/**
		 * 功能：判断两个时间是否相等 精确到分
		 * 
		 * @return
		 */
		public static boolean getCurrentDate(String strDate,Date date) {
			SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar   dar=Calendar.getInstance();    
			 try {
				dar.setTime(dft.parse(strDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			return dft.format(dar.getTime()).equals(dft.format(date));
		}
		public static Date addDate1(Date d,long day) throws ParseException {
			  
			  long time = d.getTime();
			  day = day*24*60*60*1000;
			  time+=day;
			  return new Date(time);

			  } 
		public static Date getLastMonthDay(){
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();  
			int month = calendar.get(Calendar.MONTH);
			calendar.set(Calendar.MONTH, month-1);
			calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
			Date strDateTo = calendar.getTime();  
			return strDateTo;
		}
	    public static void main(String[] args) {
//	    	getSubTwoTime("2016-04-13","2016-04-15");
//	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	System.out.println(getSubTwoTimeYY("2016-04-14","2016-04-21"));
	    }
}
