package com.czw.common.tools;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;


public class JavaTools extends StringFunction
{

	private static final long IPCheck = (long) 0x7fffffff * 2;
	
	/**
	 * 
	 */
	public static final char[] DefaultSpliter = { ',', '|', ';' };

	public static final char[] DefaultSpliter2 = { ',', '|' };

	public static final char[] LineSpliter2 = { '\n', '\r' };

	protected JavaTools()
	{
		super();
	}

	/**
	 * 将对象转换成long值
	 * 
	 * @param str
	 * @return
	 */
	public final static long toLong(Object str)
	{
		return toLong(str, -1);
	}

	/**
	 * 将对象转换成long值
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static long toLong(Object str, long defaultValue)
	{
		if (str != null)
		{
			try
			{
				Class cType = str.getClass();
				if (cType == Double.class || cType == double.class)
					return ((Double) str).longValue();
				else if (cType == Float.class || cType == float.class)
					return ((Float) str).longValue();
				else if (cType == Long.class || cType == long.class)
					return ((Long) str).longValue();
				else if (cType == Integer.class || cType == int.class)
					return ((Integer) str).longValue();
				else if (cType == Character.class || cType == char.class)
					return (long)((Character) str).charValue();
				else if (cType == Byte.class || cType == byte.class)
					return ((Byte) str).longValue();
				else if (str instanceof Date)
					return ((Date) str).getTime();
				return Long.parseLong(str.toString());
			} catch (Exception e)
			{

			}
		}
		return defaultValue;
	}

	/**
	 * 将对象转换成String
	 * @param str
	 * @return
	 */
	public final static String toString(Object str)
	{
		return toString(str, null);
	}

	/**
	 * 将对象转换成String
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static String toString(Object str, String defaultValue)
	{
		if (str != null)
		{
			if (str instanceof String)
				return (String) str;
			else
			{
				try
				{
					return str.toString();
				} catch (Exception e)
				{

				}
			}
		}
		return defaultValue;
	}

	/**
	 * 将对象转换成int值
	 * 
	 * @param str
	 * @return
	 */
	public final static int toInt(Object str)
	{
		return toInt(str, -1);
	}

	/**
	 * 
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static int toInt(Object str, int defaultValue)
	{
		if (str != null)
		{
			try
			{
				Class cType = str.getClass();
				if (cType == Double.class || cType == double.class)
					return ((Double) str).intValue();
				else if (cType == Float.class || cType == float.class)
					return ((Float) str).intValue();
				else if (cType == Long.class || cType == long.class)
					return ((Long) str).intValue();
				else if (cType == Integer.class || cType == int.class)
					return ((Integer) str).intValue();
				else if (cType == Character.class || cType == char.class)
					return (int)((Character) str).charValue();
				else if (cType == Byte.class || cType == byte.class)
					return ((Byte) str).intValue();
				return Integer.parseInt(str.toString());
			} catch (Exception e)
			{

			}
		}
		return defaultValue;
	}

	/**
	 * 将对象转换成double值
	 * 
	 * @param str
	 * @return
	 */
	public final static double toDouble(Object str)
	{
		return toDouble(str, -1);
	}

	/**
	 * 将对象转换成double值
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static double toDouble(Object str, double defaultValue)
	{
		if (str != null)
		{
			try
			{
				Class cType = str.getClass();
				if (cType == Double.class || cType == double.class)
					return ((Double) str).doubleValue();
				else if (cType == Float.class || cType == float.class)
					return ((Float) str).doubleValue();
				else if (cType == Long.class || cType == long.class)
					return ((Long) str).doubleValue();
				else if (cType == Integer.class || cType == int.class)
					return ((Integer) str).doubleValue();
				else if (cType == Character.class || cType == char.class)
					return (double)((Character) str).charValue();
				else if (cType == Byte.class || cType == byte.class)
					return ((Byte) str).doubleValue();
				else if (str instanceof Date)
					return (double)((Date) str).getTime();
				return Double.parseDouble(str.toString());
			} catch (Exception e)
			{

			}
		}
		return defaultValue;
	}

	/**
	 * 将对象转换成float值
	 * 
	 * @param str
	 * @return
	 */
	public final static float toFloat(Object str)
	{
		return toFloat(str, -1);
	}

	/**
	 * 将对象转换成float值
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static float toFloat(Object str, float defaultValue)
	{
		if (str != null)
		{
			try
			{
				Class cType = str.getClass();
				if (cType == Double.class || cType == double.class)
					return ((Double) str).floatValue();
				else if (cType == Float.class || cType == float.class)
					return ((Float) str).floatValue();
				else if (cType == Long.class || cType == long.class)
					return ((Long) str).floatValue();
				else if (cType == Integer.class || cType == int.class)
					return ((Integer) str).floatValue();
				else if (cType == Character.class || cType == char.class)
					return (float)((Character) str).charValue();
				else if (cType == Byte.class || cType == byte.class)
					return ((Byte) str).floatValue();
				return Float.parseFloat(str.toString());
			} catch (Exception e)
			{

			}
		}
		return defaultValue;
	}

	

	
	


	
	/**
	 * 获取非0最小值
	 * @return
	 */
	public final static int getMinUpZero(int... n)
	{
		int npos = -1;
		if (n != null && n.length > 0)
		{
			for (int i = 0; i < n.length; i++)
			{
				npos = getMinUpZero(npos, n[i]);
			}
		}
		return npos;
	}

	/**
	 * 获取非0最小值
	 * @return
	 */
	public final static int getMinUpZero(int n1, int n2)
	{
		return (n1 >= 0 && n2 >= 0) ? (n1 > n2 ? n2 : n1) : (n1 < n2 ? n2 : n1);
	}

	/**
	 * 判断是否属于手机号
	 * 
	 * @param mobileNum
	 * @return
	 */
	public final static boolean isMobileNum(String mobileNum)
	{
		if (mobileNum == null)
			return false;
		int len = mobileNum.length();
		if (len < 11 || len > 14)
			return false;
		if ("13888888888".equals(mobileNum) || "13811111111".equals(mobileNum) || "13800138000".equals(mobileNum))
			return false;
		if (mobileNum.charAt(len - 11) == '1')
		{
			char c = 0;
			for (int i = len - 10; i < len; i++)
			{
				c = mobileNum.charAt(i);
				if (c < '0' || c > '9')
					return false;
			}
			return true;
		}
		return false;
	}

	

	/**
	 * 判断是否为mid
	 * @param mid
	 * @return
	 * @deprecated
	 */
	public final static boolean isMid(String mid)
	{
		return mid != null && mid.length() > 10 && mid.length() < 64;
	}

	/**
	 * 判断是否包含汉字
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean noChinese(String str)
	{
		if (str != null)
		{
			char c = 0;
			for (int i = 0; i < str.length(); i++)
			{
				c = str.charAt(i);
				if (c > 0xFF || c < 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * 将ip转换成long
	 * @param ipstr
	 * @return
	 */
	public final static long getIpNum(String ipstr)
	{
		long ip = -1;
		if (ipstr != null && !ipstr.equals(""))
		{
			String[] strlist = ipstr.split("\\.");
			if (strlist != null && strlist.length == 4)
			{
				ip = 0;
				long bei = 1;
				for (int i = 3, n = 0; i >= 0; i--, bei = bei * 0x100)
				{
					n = toInt(strlist[i]);
					if (n < 0 || n > 255)
						return -1;
					ip += (long) n * (long) bei;
				}
			}
		}
		if (ip >= IPCheck)
			return -1;
		return ip;
	}
	
	/**
	 * 将一个long值转换成ip字符串
	 * @param ip
	 * @return
	 */
	public final static String numToIpString(long ip)
	{
		return new StringBuffer().append((ip >> 24) & 0xFF).append('.').append((ip >> 16) & 0xFF).append('.').append((ip >> 8) & 0xFF).append('.').append(ip & 0xFF).toString();
	}

	/**
	 * 判断是否为一个ip字符串
	 * @param ip
	 * @return
	 */
	public final static boolean isIpString(String ip)
	{
		return getIpNum(ip) > 0;
	}

	
	/**
	 * 主动垃圾内存收集
	 * 
	 */
	public final static void gc()
	{
		System.gc();
	}

	/**
	 * 将一个参数及值加入到URL中
	 * @param url
	 * @param par
	 * @return
	 */
	public final static String addParToUrl(String url, String par)
	{
		if (url != null && par != null && !"".equals(par))
		{
			int npos = url.indexOf("?");
			if (npos > 0)
			{
				if (!url.endsWith("?"))
					url += "&";
			}
			else
				url += "?";
			url += par;
		}
		return url;
	}

	

	/**
	 * 将金额格式化成文字
	 * 
	 * @param money
	 *            单位（分）
	 * @return
	 */
	public final static String getMoneyString(long money)
	{
		return getNumberString(money / 100, "###.##");
	}

	/**
	 * 按所给格式将数字格式化成文字
	 * 
	 * @param number
	 *            数字
	 * @param format
	 *            格式	###.##
	 * @return
	 */
	public final static String getNumberString(double number, String format)
	{
		return (new java.text.DecimalFormat(format)).format(number);
	}

	/**
	 * 按所给格式格式化当前时间
	 * 
	 * @param format
	 *            按SimpleDateFormat的定义（yyyy-MM-dd HH:mm:ss SSS）
	 * @return
	 */
	public final static String getTimeString(String format)
	{
		return getTimeString(-1, format);
	}

	/**
	 * 按所给格式格式化所给时间
	 * 
	 * @param time
	 *            <=0：取当前时间 >0：取所给时间
	 * @param format
	 *            按SimpleDateFormat的定义（yyyy-MM-dd HH:mm:ss SSS）
	 * @return
	 */
	public final static String getTimeString(long time, String format)
	{
		if (format != null)
		{
			if (time >= 0)
				return (new SimpleDateFormat(format)).format(new Date(time));
			return (new SimpleDateFormat(format)).format(new Date());
		}
		return "";
	}

	/**
	 * 按所给格式格式化所给时间
	 * 
	 * @param dateTime
	 *            Date对象
	 * @param format
	 *            按SimpleDateFormat的定义（yyyy-MM-dd HH:mm:ss SSS）
	 * @return
	 */
	public final static String getTimeString(Date dateTime, String format)
	{
		return getTimeString(dateTime == null ? 0 : dateTime.getTime(), format);
	}

	/**
	 * 获取指定年月日的时间戳
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public final static long getTimeMillis(int year, int month, int date)
	{
		return getTimeMillis(year, month, date, 0, 0, 0);
	}

	/**
	 * 获取所给年月日时分秒的时间戳
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public final static long getTimeMillis(int year, int month, int date, int hour, int minute, int second)
	{
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date, hour, minute, second);
		return c.getTimeInMillis();
	}

	/**
	 * 获取所给日期时间字符串及格式定义所对应的时间戳
	 * @param dateTimeString
	 * @param format
	 * @return
	 */
	public final static long getTimeMillis(String dateTimeString, String format)
	{
		if (stringIsNotNull(dateTimeString, format))
		{
			try
			{
				return (new SimpleDateFormat(format)).parse(dateTimeString).getTime();
			} catch (Exception e)
			{
			}
		}
		return 0;
	}
	
	/**
	 * 获取某时间戳的一天的开始
	 * @param dateTime
	 * @return
	 */
	public final static long getDateStartTimeMillis(long dateTime)
	{
		return dateTime > 0 ? ((dateTime + 28800000L) / 86400000L * 86400000L - 28800000L) : 0;
//		return dateTime > 0 ? dateTime - ((dateTime % 86400000L) + 28800000L) : 0;
	}

	/**
	 * 猜测日期字段，不确定分隔符是什么，但可以确定年月日时分秒的位置时使用
	 * @param dateTimeString
	 * @param format	只能是空格分隔各字段，例如：yyyy MM dd HH mm ss SSS
	 * @return
	 */
	public final static long speculateTimeMillis(String dateTimeString, String format)
	{
		if (stringIsNotNull(dateTimeString))
		{
			if(!stringIsNotNull(format))
				format = "yyyy MM dd HH mm ss SSS";
			String[] slist = dateTimeString.split("[\\/\\-\\ \\:\\,\\+]");
			StringBuffer sbuf = new StringBuffer();
			for(String s : slist)
			{
				if(sbuf.length() > 0)
					sbuf.append(' ');
				sbuf.append(s);
			}
			return getTimeMillis(sbuf.toString(), format);
		}
		return 0;
	}

	/**
	 * 返回代表日期时间的字符串的Date对象
	 * 
	 * @param dataTimeString
	 *            代表日期时间的字符串
	 * @param format
	 *            按SimpleDateFormat的定义（yyyy-MM-dd HH:mm:ss SSS）
	 * @return
	 */
	public final static Date getStringTimeDate(String dataTimeString, String format)
	{
		try
		{
			return (new SimpleDateFormat(format)).parse(dataTimeString);
		} catch (Exception e)
		{
		}
		return new Date();
	}

	/**
	 * 将一个数字格式化成一个带逗号的数字字符串
	 * 
	 * @param num
	 * @return
	 */
	public final static String num2StringNum(long num)
	{
		String str = String.valueOf(num);
		if (str.length() > 3)
		{
			StringBuffer strbuf = new StringBuffer();
			for (int i = 0, j = str.length(); i < str.length(); i++, j--)
			{
				if (i != 0 && (j % 3 == 0))
					strbuf.append(',');
				strbuf.append(str.charAt(i));
			}
			str = strbuf.toString();
		}
		return str;
	}

	/**
	 * 判断两个字符串中是否有相同的字段，使用DefaultSpliter作为字段的切分
	 * @param src
	 * @param desk
	 * @return
	 */
	public final static boolean listHaveSameItem(String src, String desk)
	{
		return listHaveSameItem(src, desk, null);
	}

	/**
	 * 判断两个字符串中是否有相同的字段
	 * @param src
	 * @param desk
	 * @param spliter	切分字符
	 * @return
	 */
	public final static boolean listHaveSameItem(String src, String desk, char[] spliter)
	{
		if (stringIsNotNull(desk) && stringIsNotNull(src))
		{
			if (spliter == null)
				spliter = DefaultSpliter;
			Set<String> srcstr = splitToSet(src.toLowerCase(), spliter);
			Set<String> deskstr = splitToSet(desk.toLowerCase(), spliter);
			if (srcstr != null && srcstr.size() > 0 && deskstr != null && deskstr.size() > 0)
			{
				for(String s : srcstr)
				{
					if(deskstr.contains(s))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将所给对象转换成布尔值
	 * @param str
	 * @return
	 */
	public final static boolean toBoolean(Object str)
	{
		return toBoolean(str, false);
	}

	/**
	 * 将所给对象转换成布尔值
	 * @param str
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static boolean toBoolean(Object str, boolean defaultValue)
	{
		boolean ok = defaultValue;
		if (str != null)
		{
			if (str instanceof Boolean)
				return ((Boolean) str).booleanValue();
			str = str.toString().toLowerCase().trim();
			if ("true".equals(str))
				ok = true;
			else if ("false".equals(str))
				ok = false;
			else if ("ok".equals(str))
				ok = true;
			else if ("on".equals(str))
				ok = true;
			else if ("yes".equals(str))
				ok = true;
			else if ("no".equals(str))
				ok = false;
			else if ("off".equals(str))
				ok = false;
			else if ("not".equals(str))
				ok = false;
			{
				int n = toInt(str);
				if (n == 0)
					ok = false;
				else if (n > 0)
					ok = true;
			}
		}

		return ok;
	}

	

	/**
	 * 将所给URL中的参数中的某个参数去掉
	 * @param str
	 * @param key
	 * @return
	 */
	public final static String removeParString(String str, String key)
	{
		if (str != null && key != null)
		{
			key = "&" + key + "=";
			for (int npos = 0, npos2 = 0; (npos = str.indexOf(key)) >= 0;)
			{
				npos2 = str.indexOf('&', npos + 2);
				str = (npos < 1 ? "" : str.substring(0, npos)) + (npos2 >= 0 ? str.substring(npos2) : "");
			}
		}
		return str;
	}

	

	
	
	

	/**
	 * 判断数组是否为空
	 * 
	 * @param strlist
	 * @param minlen
	 * @return
	 */
	public final static boolean arrayIsNotNull(String[] strlist, int minlen)
	{
		if (strlist != null && strlist.length >= minlen)
		{
			for (int i = 0; i < minlen; i++)
			{
				if (strlist[i] == null)
					return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获取数组索引对象
	 * @param a
	 * @param index
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static Object getArrayObject(Object[] a, int index)
	{
		return getArrayObject(a, index, null);
	}

	/**
	 * 获取数组索引对象
	 * @param a
	 * @param index
	 * @param defaultValue	当对象为空时的缺省值
	 * @return
	 */
	public final static Object getArrayObject(Object[] a, int index, Object defaultValue)
	{
		return (a != null && index >= 0 && index < a.length) ? a[index] : defaultValue;
	}

	/**
	 * 判断两Object是否相同
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public final static boolean equalTo(Object obj1, Object obj2)
	{
		if (obj1 != null && obj2 != null)
			return obj1.equals(obj2);
		return obj1 == null && obj2 == null;
	}

	/**
	 * 将字符串按常用分隔符分割（|或,）
	 * 
	 * @param str
	 * @return
	 */
	/*
	 * public final static String[] ToTypeList(String str) { return splitToList(str); }
	 */

	/**
	 * 将所给字符串按常用分隔符分割（|或,）
	 * 
	 * @param str
	 * @return
	 */
	public final static String[] splitToList(String str)
	{
		return splitToList(str, DefaultSpliter2);
	}

	/**
	 * 切分成行
	 * 
	 * @param str
	 * @return
	 */
	public final static String[] splitToLines(String str)
	{
		return str != null ? removeAll(str, "\r").split("\\\n") : null;
	}

	/**
	 * 按顺序将字符串分离成队列
	 * @param str
	 * @param spliter
	 * @return
	 */
	public final static String[] splitToList(String str, char[] spliter)
	{
		List<String> l = splitToArrayList(str, spliter);
		if (l != null)
		{
			String[] s = new String[l.size()];
			l.toArray(s);
			return s;
		}
		return null;
	}

	/**
	 * 将字符串分离成队列，保证Key的唯一性
	 * @param str
	 * @param spliter
	 * @return
	 */
	public final static String[] splitToUniqList(String str, char[] spliter)
	{
		Set<String> l = splitToSet(str, spliter);
		if (l != null)
		{
			String[] s = new String[l.size()];
			l.toArray(s);
			return s;
		}
		return null;
	}

	/**
	 * 将字符串按所给切分字符，切分成段，放入List
	 * @param str
	 * @param spliter
	 * @return
	 */
	public final static List<String> splitToArrayList(String str, char[] spliter)
	{
		if (str != null && spliter != null)
		{
			List<String> l = new LinkedList<String>();
			StringBuffer strbuf = new StringBuffer();
			char c = 0;
			boolean isSpliter = false;
			for (int i = 0, j = 0; i < str.length(); i++)
			{
				isSpliter = false;
				c = str.charAt(i);
				for (j = 0; j < spliter.length; j++)
				{
					if (c == spliter[j])
					{
						l.add(strbuf.toString().trim());
						strbuf.setLength(0);
						isSpliter = true;
						break;
					}
				}
				if (!isSpliter)
					strbuf.append(c);
			}
			if (strbuf.length() > 0)
				l.add(strbuf.toString().trim());
			if (l.size() > 0)
				return l;
		}
		return null;
	}

	/**
	 * 将字符串按所给切分字符，切分成段，放入Set，即：无重复字符串
	 * @param str
	 * @param spliter
	 * @return
	 */
	public final static Set<String> splitToSet(String str, char[] spliter)
	{
		if (str != null && spliter != null)
		{
			Set<String> l = new LinkedHashSet<String>();
			StringBuffer strbuf = new StringBuffer();
			char c = 0;
			boolean isSpliter = false;
			for (int i = 0, j = 0; i < str.length(); i++)
			{
				isSpliter = false;
				c = str.charAt(i);
				for (j = 0; j < spliter.length; j++)
				{
					if (c == spliter[j])
					{
						if (strbuf.length() > 0)
						{
							l.add(strbuf.toString().trim());
							strbuf.setLength(0);
						}
						isSpliter = true;
						break;
					}
				}
				if (!isSpliter)
					strbuf.append(c);
			}
			if (strbuf.length() > 0)
				l.add(strbuf.toString().trim());
			if (l.size() > 0)
				return l;
		}
		return null;
	}

	/**
	 * 将毫秒转化成 小时:分钟:秒
	 * 
	 * @param time
	 *            毫秒
	 * @return
	 */
	public final static String getTimeLastString(long time)
	{
		long t = time / 1000;
		return formatNumberString(t / 3600, 2) + ":" + formatNumberString(t / 60 % 60, 2) + ":"
		        + formatNumberString(t % 60, 2);
	}

	/**
	 * 将数字格式化成固定位数
	 * 
	 * @param n
	 *            数字
	 * @param wei
	 *            位数
	 * @return
	 */
	public final static String formatNumberString(long n, int wei)
	{
		String s = String.valueOf(n);
		while (s.length() < wei)
			s = "0" + s;
		return s;
	}

	/**
	 * 将小数格式化成固定小数点前位数和小数点后位数
	 * 
	 * @param n
	 *            数字
	 * @param wei
	 *            小数点前位数
	 * @param point
	 *            小数点后位数
	 * @return
	 */
	public final static String formatNumberString(double n, int wei, int point)
	{
		long pwei = 1;
		for (int i = 0; i < point; i++)
			pwei *= 10;
		return formatNumberString((long) n, wei)
		        + ((n - (long) n > 0) ? "." + formatNumberString((long) ((n - (long) n) * pwei), point) : "");
	}

	/**
	 * 获取字节数，翻译成可读性强的表达方式
	 * 
	 * @param nbyte
	 * @return
	 */
	public final static String getByteHuman(long nbyte)
	{
		if (nbyte < 1024)
			return nbyte + " B";
		else if (nbyte < 1048576)
			return (nbyte / 1024) + "." + ((nbyte % 1024) * 10 / 1024) + " KB";
		return (nbyte / 1048576) + "." + ((nbyte % 1048576) * 10 / 1048576) + " MB";
	}

	/**
	 * 获取经过格式化好的日志
	 * 
	 * @param obj
	 * @return
	 */
	public final static String logString(Object... obj)
	{
		return (new LogString()).append(obj).toString();
	}

	/**
	 * 格式化exception日志
	 * 
	 * @param e
	 * @param obj
	 * @return
	 */
	public final static String exceptionLogString(Throwable e, Object... obj)
	{
		return Log.errorString((new LogString()).append(obj).toString(), e);
	}

	

	/**
	 * 从inputStream中读取数据转换为byte数组
	 * 
	 * @param in
	 *            inputStream对象
	 * @param maxLength
	 *            < 0 : 读入全部
	 *            > 0 : 读入指定长度
	 *            = 0 : 不读入
	 * @return byte数组
	 */
	public final static byte[] inputStreamToByteArray(InputStream in, long maxLength, boolean closeIn)
	{
		if (in != null && maxLength != 0)
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try
			{
				byte[] b = new byte[1024];
				for (int nbyte = 0, allbyte = 0; (maxLength <= 0 || allbyte < maxLength) && (nbyte = in.read(b)) >= 0; allbyte += nbyte)
				{
					if (nbyte > 0)
						out.write(b, 0, nbyte);
					else
						Thread.sleep(1);
				}
			} catch (Exception e)
			{
			}

			try
			{
				out.close();
			} catch (Exception e)
			{
			}
			if (closeIn)
				try
				{
					in.close();
				} catch (Exception e)
				{
				}

			if (out.size() > 0)
				return out.toByteArray();
		}
		return null;
	}

	/**
	 * 从inputStream中读取数据转换为byte数组
	 * 
	 * @param in
	 *            inputStream对象
	 * @param maxLength <
	 *            0 : 读入全部 > 0 : 读入指定长度 = 0 : 不读入
	 * @return byte数组
	 */
	public final static byte[] inputStreamToByteArray(InputStream in, long maxLength)
	{
		return inputStreamToByteArray(in, maxLength, true);
	}

	/**
	 * 以安全的方式sleep
	 * @param time
	 */
	public final static void sleep(long time)
	{
		try
		{
			Thread.sleep(time);
			Thread.yield();
		} catch (Exception e)
		{
		}
	}

	// ///////////////////////////////////////////////////////外部方法
	

	
	/**
	 * 将List中的对象排序按升序排列
	 * 
	 * @param l
	 */
	@SuppressWarnings("unchecked")
	public final static void sortArray(List l)
	{
		if (l != null && l.size() > 0)
		{
			Object[] ll = l.toArray();
			Arrays.sort(ll);
			l.clear();
			for (int i = 0; i < ll.length; i++)
				l.add(ll[i]);
		}
	}

	/**
	 * 将map内的值根据key进行排序，传入的map需要得是有序map，例如LinkedHashMap
	 * @param m
	 * @param sortByKey
	 */
	@SuppressWarnings("unchecked")
	public final static void sortMap(Map m, boolean sortByKey)
	{
		class SortData implements Comparable
		{

			Entry data;
			boolean sortByKey;

			@Override
			public int compareTo(Object o)
			{
				if (o != null && o instanceof Entry)
				{
					if (sortByKey)
					{
						if (data.getKey() != null && ((Entry) o).getKey() != null
						        && data.getKey() instanceof Comparable && ((Entry) o).getKey() instanceof Comparable)
						{
							return ((Comparable) data.getKey()).compareTo(((Entry) o).getKey());
						}
					}
					else
					{
						if (data.getValue() != null && ((Entry) o).getValue() != null
						        && data.getValue() instanceof Comparable
						        && ((Entry) o).getValue() instanceof Comparable)
						{
							return ((Comparable) data.getValue()).compareTo(((Entry) o).getValue());
						}
					}
				}
				return -1;
			}
		}
		;
		if (m != null && m.size() > 0)
		{
			SortData[] sd = new SortData[m.size()];
			Iterator it = m.entrySet().iterator();
			for (int i = 0; it.hasNext(); i++)
			{
				sd[i] = new SortData();
				sd[i].data = (Entry) it.next();
				sd[i].sortByKey = sortByKey;
			}
			Arrays.sort(sd);
			m.clear();
			for (int i = 0; i < sd.length; i++)
				m.put(sd[i].data.getKey(), sd[i].data.getValue());
		}
	}
	
	

	private static final Random random = new Random(System.currentTimeMillis());

	public final static long Random()
	{
		long res = random.nextLong();
		return res >= 0 ? res : 0 - res;
	}

	public final static long Random(long min, long max)
	{
		if (min < 0)
			min = 0;
		long n = Random();
		if (max > min)
			n = n % (max - min);
		if (min > 0)
			n += min;
		return n;
	}

	/**
	 * 从所给数组里随机挑一个
	 * @param list
	 * @return
	 */
	public final static Object pickOne(Object[] list)
	{
		if (list != null && list.length > 0)
			return list[(int) Random(0, list.length)];
		return null;
	}

	/**
	 * 从对象中以某个关键字取出所包含的对象
	 * @param o
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static Object getObjectFromObject(Object o, Object key)
	{
		if (o != null && o instanceof Map && key != null)
		{
			Object oo = ((Map) o).get(key);
			if (oo != null)
				return oo;
		}
		return null;
	}

	/**
	 * 从对象中以某个关键字取出所包含的字符串
	 * @param o
	 * @param key
	 * @return
	 */
	public final static String getStringFromObject(Object o, Object key)
	{
		Object oo = getObjectFromObject(o, key);
		if (oo != null && oo instanceof String)
			return toString(oo);
		return null;
	}

	/**
	 * 从对象中以某个关键字取出所包含的Map
	 * @param o
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static Map getMapFromObject(Object o, Object key)
	{
		Object oo = getObjectFromObject(o, key);
		if (oo != null && oo instanceof Map)
			return (Map) oo;
		return null;
	}

	/**
	 * 
	 * @param o
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static List getListFromObject(Object o, Object key)
	{
		Object oo = getObjectFromObject(o, key);
		if (oo != null && oo instanceof List)
			return (List) oo;
		return null;
	}

	/**
	 * 从对象中以某个关键字取出所包含的List
	 * @param o
	 * @return
	 */
	public final static List<Object> makeObjectList(Object... o)
	{
		List<Object> l = new ArrayList<Object>();
		if (o != null && o.length > 0)
		{
			for (Object oo : o)
			{
				l.add(oo);
			}
		}
		return l;
	}

	/**
	 * 把一堆对象两两成对，放入一个map
	 * @param o
	 * @return
	 */
	public final static Map<Object, Object> makeObjectMap(Object... o)
	{
		Map<Object, Object> m = new HashMap<Object, Object>();
		if (o != null && o.length > 0)
		{
			for (int i = 0; i + 1 < o.length; i += 2)
			{
				m.put(o[i], o[i + 1]);
			}
		}
		return m;
	}
	
	private static final String checkPattern = "^([a-z0-9A-Z]+[\\_|\\-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 判断所给字符串
	 * @param emailAddress
	 * @return
	 */
	public final static boolean isEmail(String emailAddress)
	{
		// String checkPattern =
		// "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$";
		return emailAddress != null && emailAddress.matches(checkPattern);
	}

	/**
	 * 获取几个日期的最大值
	 * @param d
	 * @return
	 */
	public final static long getMaxDateTimeLong(Date... d)
	{
		long t = 0;
		if (d != null)
		{
			for (Date dd : d)
			{
				if (dd != null && dd.getTime() > t)
					t = dd.getTime();
			}
		}
		return t;
	}

	/**
	 * 初步判断是否为正确的身份证号
	 * @param id
	 * @return
	 */
	public final static boolean isRightIdentityCardId(String id)
	{
		return IdCard.isRightIdentityCardId(id);
	}

	/**
	 * 初步判断是否为正确的身份证名称
	 * @param name
	 * @return
	 */
	public final static boolean isRightIdentityCardName(String name)
	{
		return IdCard.isRightIdentityCardName(name);
	}

	/**
	 * 通过身份证号判断性别
	 * 
	 * @param id
	 * @return 0: 女 1: 男 -1: 错误的身份证号
	 */
	public final static int getIdentityCardNumSex(String id)
	{
		return IdCard.getIdentityCardNumSex(id);
	}

	/**
	 * 随机排列数据列表
	 * 
	 * @param list
	 *            需要被xx的数据列表
	 */
	@SuppressWarnings("unchecked")
	public final static void randomCollectionValues(Collection list)
	{
		randomCollectionValues(list, -1);
	}

	/**
	 * 随机排列数据列表
	 * 
	 * @param list
	 *            需要被xx的数据列表
	 * @param max
	 *            最多留下多少个
	 */
	@SuppressWarnings("unchecked")
	public final static void randomCollectionValues(Collection list, int max)
	{
		if (list != null && list.size() > 0)
		{
			if (max < 0 || max > list.size())
				max = list.size();
			if (max > 0)
			{
				List<Object> lbuf = new ArrayList<Object>(list.size());
				lbuf.addAll(list);
				list.clear();
				Random r = new Random(System.currentTimeMillis());
				for (int i = 0, n = 0; i < max; i++)
				{
					n = Math.abs(r.nextInt() % lbuf.size());
					list.add(lbuf.get(n));
					lbuf.remove(n);
				}
			}
			else
				list.clear();
		}
	}

	/**
	 * 读入8位整型
	 * @param srcBytes
	 * @param begin
	 * @return
	 */
	public final static int readInt8(byte[] srcBytes, int begin)
	{
		return (int) readInt(srcBytes, begin, 1);
	}

	/**
	 * 读入16位整型
	 * @param srcBytes
	 * @param begin
	 * @return
	 */
	public final static int readInt16(byte[] srcBytes, int begin)
	{
		return (int) readInt(srcBytes, begin, 2);
	}

	/**
	 * 读入32位整型
	 * @param srcBytes
	 * @param begin
	 * @return
	 */
	public final static long readInt32(byte[] srcBytes, int begin)
	{
		return readInt(srcBytes, begin, 4);
	}

	/**
	 * 读入64位整形
	 * @param srcBytes
	 * @param begin
	 * @return
	 */
	public final static long readInt64(byte[] srcBytes, int begin)
	{
		return readInt(srcBytes, begin, 8);
	}

	/**
	 * 读入整型
	 * @param srcBytes	字节数组
	 * @param begin		开始索引
	 * @param nbyte		读出字节数
	 * @return
	 */
	public final static long readInt(byte[] srcBytes, int begin, int nbyte)
	{
		long n = 0;
		if (srcBytes != null && srcBytes.length >= begin + nbyte)
		{
			for (int b = begin, j = nbyte - 1, i = b; j >= 0; i++, j--)
			{
				n |= ((long) srcBytes[i] & (long) 0xFF) << (8 * j);
			}
		}
		else
			n = -1;
		return n;
	}

	/**
	 * 写入8位整型
	 * @param n
	 * @param destBytes
	 * @param begin
	 */
	public final static void writeInt8(int n, byte[] destBytes, int begin)
	{
		writeInt(n, destBytes, begin, 1);
	}

	/**
	 * 写入16位整型
	 * @param n
	 * @param destBytes
	 * @param begin
	 */
	public final static void writeInt16(int n, byte[] destBytes, int begin)
	{
		writeInt(n, destBytes, begin, 2);
	}

	/**
	 * 写入32位整型
	 * @param n
	 * @param destBytes
	 * @param begin
	 */
	public final static void writeInt32(int n, byte[] destBytes, int begin)
	{
		writeInt(n, destBytes, begin, 4);
	}

	/**
	 * 写入64位整型
	 * @param n
	 * @param destBytes
	 * @param begin
	 */
	public final static void writeInt64(long n, byte[] destBytes, int begin)
	{
		writeInt(n, destBytes, begin, 8);
	}

	/**
	 * 写入整型
	 * @param n			整型值
	 * @param destBytes	目标字节数组
	 * @param begin		开始索引值
	 * @param length	写入字节长度
	 */
	public final static void writeInt(long n, byte[] destBytes, int begin, int length)
	{
		long xx = 0xFF;
		for (int i = 0,j = begin + length - 1; i < length; i++, j --)
		{
			destBytes[j] = (byte) ((n & xx) >> (8 * i));
			xx = xx << 8;
		}
	}

    /**
     * 将字节数组转换成数值，根据指定字节数决定数值类型
     * @param data
     * @param nbyte
     * @return
     */
    public final static long byteArrayToNumber(byte[] data, int nbyte)
    {
        long n = 0;
        if(data.length >= nbyte)
        {
            for(int j = nbyte - 1, i = 0;j >= 0;i ++, j--)
            {
                n += ((long)data[i] & (long)0xFF) << (8 * j);
            }
        }
        else
            n = -1;
        return n;
    }
    
    /**
     * 将所给数值转换成指定长度的字节数组
     * @param n
     * @param length
     * @return
     */
    public final static byte[] numberToByteArray(long n, int length)
    {
        byte[] data = new byte[length];
        long xx = 0xFF;
        for(int i = 0,j = length-1;j >= 0;i ++, j --)
        {
            data[j] = (byte)((n & xx) >> (8 * i));
            xx = xx << 8;
        }
        return data;
    }
    
    /**
     * 将所给数值转换成指定长度的字节数组，方法2
     * @param n
     * @param length
     * @return
     */
    public final static byte[] numberToByteArray2(long n, int length)
    {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	DataOutputStream dout = new DataOutputStream(out);
		try
        {
	    	switch(length)
	    	{
	    		case 1:
		            dout.writeByte((byte)n);
	    			break;
	    		case 2:
	    	    	dout.writeShort((short)n);
	    			break;
	    		case 4:
	    	    	dout.writeInt((int)n);
	    			break;
	    		case 8:
	    	    	dout.writeLong(n);
	    			break;
	    	}
	    	dout.flush();
	    	out.flush();
        } catch (Exception e)
        {
        }
    	try
        {
	        dout.close();
        } catch (Exception e)
        {
        }
    	try
        {
    		out.close();
        } catch (Exception e)
        {
        }
        if(out.size() > 0)
        	return out.toByteArray();
        return new byte[length];
    }

	/**
	 * int数组转化为byte数组
	 * 
	 * @param longArray
	 * @return
	 */
	public final static byte[] intArrayToByteArray(long[] intArray)
	{
		if (intArray == null || intArray.length < 1)
			return null;
		byte[] byteArray = new byte[intArray.length * 4];
		for (int i = 0; i < intArray.length; i++)
		{
			byteArray[0 + 4 * i] = (byte) (intArray[i] >> 24);
			byteArray[1 + 4 * i] = (byte) (intArray[i] >> 16);
			byteArray[2 + 4 * i] = (byte) (intArray[i] >> 8);
			byteArray[3 + 4 * i] = (byte) (intArray[i] >> 0);
		}
		return byteArray;
	}

	/**
	 * byte数组转化为int数组
	 * 
	 * @param byteArray
	 * @return
	 */
	public final static long[] byteArrayToIntArray(byte[] byteArray)
	{
		if (byteArray == null || byteArray.length < 4)
			return null;
		long[] intArray = new long[byteArray.length / 4];
		for (int i = 0; i < intArray.length; i++)
		{
			intArray[i] = (((long) byteArray[0 + 4 * i] & 0xff) << 24) | (((long) byteArray[1 + 4 * i] & 0xff) << 16)
			        | (((long) byteArray[2 + 4 * i] & 0xff) << 8) | (((long) byteArray[3 + 4 * i] & 0xff) << 0);
		}
		return intArray;
	}

	/**
	 * long数组转化为byte数组
	 * 
	 * @param longArray
	 * @return
	 */
	public final static byte[] longArrayToByteArray(long[] longArray)
	{
		if (longArray == null || longArray.length < 1)
			return null;
		byte[] byteArray = new byte[longArray.length * 8];
		for (int i = 0; i < longArray.length; i++)
		{
			byteArray[0 + 8 * i] = (byte) (longArray[i] >> 56);
			byteArray[1 + 8 * i] = (byte) (longArray[i] >> 48);
			byteArray[2 + 8 * i] = (byte) (longArray[i] >> 40);
			byteArray[3 + 8 * i] = (byte) (longArray[i] >> 32);
			byteArray[4 + 8 * i] = (byte) (longArray[i] >> 24);
			byteArray[5 + 8 * i] = (byte) (longArray[i] >> 16);
			byteArray[6 + 8 * i] = (byte) (longArray[i] >> 8);
			byteArray[7 + 8 * i] = (byte) (longArray[i] >> 0);
		}
		return byteArray;
	}

	/**
	 * byte数组转化为long数组
	 * 
	 * @param byteArray
	 * @return
	 */
	public final static long[] byteArrayToLongArray(byte[] byteArray)
	{
		if (byteArray == null || byteArray.length < 8)
			return null;
		long[] longArray = new long[byteArray.length / 8];
		for (int i = 0; i < longArray.length; i++)
		{
			longArray[i] = (((long) byteArray[0 + 8 * i] & 0xff) << 56) | (((long) byteArray[1 + 8 * i] & 0xff) << 48)
			        | (((long) byteArray[2 + 8 * i] & 0xff) << 40) | (((long) byteArray[3 + 8 * i] & 0xff) << 32)
			        | (((long) byteArray[4 + 8 * i] & 0xff) << 24) | (((long) byteArray[5 + 8 * i] & 0xff) << 16)
			        | (((long) byteArray[6 + 8 * i] & 0xff) << 8) | (((long) byteArray[7 + 8 * i] & 0xff) << 0);
		}
		return longArray;
	}
	
	/**
	 * 获取对象中的信息，弱化对象类型，可深入读取
	 * 可支持的对象，Object，List，Map，Array
	 * @param o
	 * @param keys
	 * @return
	 */
	public final static Object getObjectByKeys(Object o, String ... keys)
	{
		if (keys != null && keys.length > 0 && o != null)
		{
			Object obj = o;
			for (String k : keys)
			{
				if (stringIsNotNull(k))
				{
					obj = getObjectByKey(obj, k);
					if (obj == null)
						break;
				}
				else
					return null;
			}
			return obj;
		}
		return null;
	}

	/**
	 * 获取对象中的信息，弱化对象类型，可深入读取
	 * 可支持的对象，Object，List，Map，Array
	 * @param <T>
	 * @param o
	 * @param key
	 * @param valueType
	 * @return
	 */
	public final static <T> T getObjectByKey(Object o, String key, Class<T> valueType)
	{
		Object oo = getObjectByKey(o, key);
		if(oo != null)
		{
			return translateObjectValueType(oo, valueType);
		}
		return null;
	}
	
	/**
	 * 将指定对象翻译成指定class类型，方法2
	 * @param <T>
	 * @param o
	 * @param valueType
	 * @return
	 */
	public final static <T> T translateObjectValueType2(Object o, Class<T> valueType)
	{
		if(o != null && valueType != null)
		{
			if(valueType == Object.class)
				return (T)o;
            try
            {
            	Constructor<T> constructor = valueType.getConstructor(o.getClass());
	            return constructor.newInstance(o);
            } catch (Exception e)
            {
            }
		}
		return null;
	}
	
	/**
	 * 将指定对象翻译成指定class类型
	 * @param <T>
	 * @param o
	 * @param valueType
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public final static <T> T translateObjectValueType(Object o, Class<T> valueType)
	{
		if(o != null && valueType != null)
		{
			if(valueType.isInstance(o))
				return (T)o;
		}
		return null;
	}

	/**
	 * 获取对象中的信息，弱化对象类型
	 * 可支持的对象，Object，List，Map，Array
	 * @param o
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static Object getObjectByKey(Object o, String key)
	{
		if (o != null && stringIsNotNull(key))
		{
			if(key.indexOf('.') >= 0)
			{
				String[] keys = key.split("\\.");
				if(keys != null && keys.length > 1)
				{
					return getObjectByKeys(o, keys);
				}
			}
			if (o.getClass().isArray())
			{
				int index = toInt(key, -1);
				if (index >= 0 && index < Array.getLength(o))
					return Array.get(o, index);
				return null;
			}
			else if (o instanceof Map)
			{
				return ((Map) o).get(key);
			}
			else if (o instanceof List)
			{
				int index = toInt(key, -1);
				if (index >= 0 && index < ((List) o).size())
					return ((List) o).get(index);
				return null;
			}
			else if (o instanceof Collection)
			{
				int index = toInt(key, -1);
				if (index >= 0 && index < ((Collection) o).size())
				{
					for(Object oo : ((Collection)o))
					{
						if(index == 0)
							return oo;
						index --;
					}
				}
				return null;
			}
			else
			{
				try
				{
					Class<?> c = o.getClass();
					Field f = null;
					while(f == null && c != null)
					{
						try
						{
							f = c.getDeclaredField(key);
						} catch (Exception e)
						{
						}
						if(f != null)
							break;
						try
						{
							c = c.getSuperclass();
						} catch (Exception e)
						{
						}
					}
					if(f != null)
					{
						f.setAccessible(true);
						return f.get(o);
					}
				} catch (Exception e)
				{
				}
			}
		}
		return null;
	}
	
	/**
	 * 设置对象中的信息，弱化对象类型
	 * 可支持的对象，Object，List，Map，Array
	 * @param o
	 * @param value
	 * @param keys
	 */
	public final static void setObjectDataByKey(Object o, Object value, String ... keys)
	{
		if (keys != null && keys.length > 0)
		{
			Object obj = o;
			for(int i = 0;i < keys.length - 1;i ++)
			{
				if (stringIsNotNull(keys[i]))
				{
					obj = getObjectByKey(obj, keys[i]);
					if (obj == null)
						return;
				}
				else
					return;
			}
			if(obj != null)
				setObjectDataByKey(obj, value, keys[keys.length - 1]);
		}
	}
	
	/**
	 * 设置对象中的信息，弱化对象类型
	 * 可支持的对象，Object，List，Map，Array
	 * @param o
	 * @param value
	 * @param key
	 */
	public final static void setObjectDataByKey(Object o, Object value, String key)
	{
		if (o != null && stringIsNotNull(key))
		{
			if (o.getClass().isArray())
			{
				int index = toInt(key, -1);
				if (index >= 0 && index < Array.getLength(o))
					Array.set(o, index, value);
			}
			else if (o instanceof Map)
			{
				((Map) o).put(key, value);
			}
			else if (o instanceof List)
			{
				int index = toInt(key, -1);
				if (index >= 0 && index < ((List) o).size())
					((List) o).set(index, value);
			}
			else
			{
				try
				{
					Class<?> c = o.getClass();
					Field f = null;
					while(f == null && c != null)
					{
						try
						{
							f = c.getDeclaredField(key);
						} catch (Exception e)
						{
						}
						if(f != null)
							break;
						try
						{
							c = c.getSuperclass();
						} catch (Exception e)
						{
						}
					}
					if(f != null)
					{
						f.setAccessible(true);
						f.set(o, value);
					}
				} catch (Exception e)
				{
				}
			}
		}
	}

	/**
	 * 获取资源文件路径
	 * @param fileName
	 * @return
	 */
	public final static String getResourceFileName(String fileName)
	{
		return replaceAll(JavaTools.class.getResource("/").getPath(), "%20", " ") + fileName;
	}
	
	
	
	/**
	 * 比较传入时间和当前时间相差天数
	 * @param begin
	 * @param pattern
	 * @return
	 */
	public final static int countDays(String date,String pattern){
		  DateFormat df = new SimpleDateFormat(pattern);
		 String bf= df.format(new Date());
		return countDays(bf,date,pattern);
	} 
	
	
	/**
	 * 比较两个时间相差天数 
	 * @param begin
	 * @param end
	 * @param pattern
	 * @return
	 */
	public final static int countDays(String begin,String end,String pattern){
		  int days =-1;
		  DateFormat df = new SimpleDateFormat(pattern);
		  Calendar c_b = Calendar.getInstance();
		  Calendar c_e = Calendar.getInstance();
		  try{
		   c_b.setTime(df.parse(begin));
		   c_e.setTime(df.parse(end));
		   if(c_b.before(c_e)){
				  Integer dd=c_b.get(Calendar.DAY_OF_YEAR);
				  Integer dd1=c_e.get(Calendar.DAY_OF_YEAR);
				  return dd1-dd;  
		   }
		  }catch(ParseException pe){
		  }
		  return days; 
	}
	
    private final static char[] DefaultSocketAddressSpliter = {',',' ',';'};
    
    /**
     * 将地址列表转换成InetSocketAddress地址列表
     * @param addrs
     * @return
     */
    public final static InetSocketAddress[] getInetSocketAddress(String ... addrs)
    {
    	if(addrs != null && addrs.length > 0)
    	{
	    	List<InetSocketAddress> l = new ArrayList<InetSocketAddress>();
	    	String[] addl;
	    	InetSocketAddress adddd;
	    	for(String add : addrs)
	    	{
	    		if(add != null)
	    		{
		    		addl = splitToUniqList(trimBlankAndReturnChar(add), DefaultSocketAddressSpliter);
		    		for(String addd : addl)
		    		{
		    			if(addd != null)
		    			{
		    				adddd = getOneInetSocketAddress(addd.trim());
		    				if(adddd != null)
		    					l.add(adddd);
		    			}
		    		}
	    		}
	    	}
	    	if(l.size() > 0)
	    	{
	    		InetSocketAddress[] adds = new InetSocketAddress[l.size()];
	    		l.toArray(adds);
	    		return adds;
	    	}
    	}
    	return null;
    }
    
    /**
     * 将地址转换成InetSocketAddress
     * @param addrs
     * @return
     */
    public final static InetSocketAddress getOneInetSocketAddress(String addrs)
    {
    	if(stringIsNotNull(addrs))
    	{
			String[] adddd = addrs.split(":");
			if(arrayIsNotNull(adddd, 2))
			{
				int port = toInt(adddd[1].trim());
				if(port > 0 && port < 65536)
				{
					try
					{
	    				return new InetSocketAddress(adddd[0].trim(), port);
					}
					catch(Exception e)
					{
						
					}
				}
			}
    	}
    	return null;
    }
    
    /**
     * 将对象转换成json字符串
     * @param o
     * @return
     */
    public final static String objectToJsonString(Object o)
    {
    	if(o != null)
    	{
	    	try
	        {
	    		ObjectMapper mapper = new ObjectMapper();
	    		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	    		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
//	    		mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, true);
	    		
		        return mapper.writeValueAsString(o);
	        } catch (Exception e)
	        {
	        	System.out.println("objectToJsonString error: " + o);
	        	e.printStackTrace();
	        }
    	}
        return null;
    }
    
    /**
     * 将对象转换成json字符数组
     * @param o
     * @param encode
     * @return
     */
    public final static byte[] objectToJsonStringBytes(Object o, String encode)
    {
    	String s = objectToJsonString(o);
    	if(s != null)
    	{
    		if(encode != null)
    		{
    			try
                {
	                return s.getBytes(encode);
                } catch (Exception e)
                {
                }
    		}
    		else
    			return s.getBytes();
    	}
    	return null;
    }
    
    /**
     * 将json字符串转换成对象
     * @param jsonString
     * @return
     */
    public final static Object jsonStringToObject(String jsonString)
    {
        return jsonStringToObject(jsonString, Object.class);
    }
    
    /**
     * 将json字符串转换成List
     * @param jsonString
     * @return
     */
    public final static List jsonStringToList(String jsonString)
    {
        return jsonStringToObject(jsonString, List.class);
    }
    
    /**
     * 将json字符串转换成对象，并指定对象类型
     * @param <T>
     * @param jsonString
     * @param valueType
     * @return
     */
    public final static <T> T jsonStringToObject(String jsonString, Class<T> valueType)
    {
    	if(jsonString != null)
    	{
    		jsonString = jsonString.trim();
    		if(!"".equals(jsonString))
    		{
		    	try
		        {
		    		ObjectMapper mapper = new ObjectMapper();
		    		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	//	    		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
			        return mapper.readValue(jsonString, valueType);
		        } catch (Exception e)
		        {
		        	System.out.println("jsonStringToObject error: " + jsonString);
		        	e.printStackTrace();
		        }
    		}
    	}
        return null;
    }

    /**
     * 生成手机验证码
     * @return
     */
    public final static String createSNSVilidateCode(){
		StringBuilder code = new StringBuilder();
		int num = 5;
		Random random = new Random();
		for(int i=0;i<num;i++){
			int m = random.nextInt(10);
			code.append(m);
		}
		return code.toString();
	}
    /**
     * 年龄转日期
     * @param age
     * @return
     */
    public final static Date ageToDate(int age){
    	Long yt=1000*60*60*24*365L;
		Date nowDate=new Date(); 
		return new Date(nowDate.getTime()-yt*age);  
    }
    /**
     * 日期转年龄
     * @return
     */
    public final static int dateToAge(Date date){
    	Date dt1= new Date();
		Long i= (dt1.getTime() - date.getTime())/(1000*60*60*24); 
		return Integer.parseInt(i/365+"");   
    }

    private static final Map<String, Map<String, List<Method>>> classMethodCache = new HashMap<String, Map<String, List<Method>>>();
    
    /**
     * 获取指定类的指定方法对象
     * @param className
     * @param methodName
     * @param parameters
     * @return
     */
    public final static Method getClassMethod(String className, String methodName, Object[] parameters)
    {
    	return getClassMethod(className, methodName, parameters, true);
    }
    
    /**
     * 获取指定类的指定方法对象
     * @param className
     * @param methodName
     * @param parameters
     * @param caseParametersInstance	每一个参数类型是否都要匹配
     * @return
     */
    public final static Method getClassMethod(String className, String methodName, Object[] parameters, boolean caseParametersInstance)
    {
    	if(stringIsNotNull(className, methodName))
    	{
    		className = className.trim();
    		methodName = methodName.trim();
	    	Map<String, List<Method>> cm = classMethodCache.get(className);
	    	if(cm == null)
	    	{
	    		synchronized(classMethodCache)
	    		{
	    			cm = classMethodCache.get(className);
	    	    	if(cm == null)
	    	    	{
	    	    		cm = new HashMap<String, List<Method>>();
	    	    		classMethodCache.put(className, cm);
	    	    	}
	    		}
	    	}
	    	if(cm != null)
	    	{
	    		List<Method> lm = cm.get(methodName);
	    		if(lm == null)
	    		{
		    		synchronized(cm)
		    		{
		    			lm = cm.get(methodName);
		    	    	if(lm == null)
		    	    	{
		    	    		lm = new ArrayList<Method>();
		    	    		cm.put(methodName, lm);

		    	            try
		    	            {
		    	            	Class c = Class.forName(className);
		    	            	while(c != null)
		    	            	{
			    					Method[] ml = c.getDeclaredMethods();
			    					if(ml != null && ml.length > 0)
			    					{
			    						for(Method m : ml)
			    						{
			    							if(m.getName().equals(methodName))
			    								lm.add(m);
			    						}
			    					}
		    	            		c = c.getSuperclass();
		    	            	}
		    	            } catch (Exception e)
		    	            {
		    	            }
		    	    	}
		    		}
	    		}
	    		if(lm != null)
	    		{
					Class[] cl = null;
					Method runm = null;
					Method funm = null;
					int i = 0;
					boolean caseto = false;
					for(Method m : lm)
					{
						if(funm == null)
							funm = m;
						cl = m.getParameterTypes();
						if(cl != null && cl.length > 0)
						{
							if(parameters != null && parameters.length == cl.length)
							{
								caseto = true;
								if(caseParametersInstance)
								{
									for(i = 0;i < cl.length && caseto;i ++)
									{
										if(parameters[i] != null)
											caseto &= cl[i].isInstance(parameters[i]);
									}
								}
								if(caseto)
								{
									runm = m;
									break;
								}
							}
						}
						else if(parameters == null || parameters.length == 0)
						{
							runm = m;
							break;
						}
					}
					if(runm == null)
						runm = funm;
					if(runm != null)
					{
						return runm;
					}
	    		}
	    	}
    	}
    	return null;
    }
    
	/**
	 * 运行指定的类的指定静态方法
	 * @param className
	 * @param methodName
	 * @param parameters
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("unchecked")
    public final static Object runClassMethod(String className, String methodName, Object[] parameters) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if(stringIsNotNull(className, methodName))
		{
			Method m = getClassMethod(className, methodName, parameters);
			if(m != null)
			{
				if(Modifier.isStatic(m.getModifiers()))
				{
					m.setAccessible(true);
		            return m.invoke(null, parameters);
				}
			}
		}
		return null;
	}
	
	/**
	 * 运行指定的类的指定方法3
	 * @param classObject
	 * @param methodName
	 * @param parameters
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
    public final static Object runClassMethod3(Object classObject, String methodName, Object[] parameters) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if(classObject != null && stringIsNotNull(methodName))
		{
			Method m = getClassMethod(classObject.getClass().getName(), methodName, parameters);
			if(m != null)
			{
				m.setAccessible(true);
	            return m.invoke(classObject, parameters);
			}
		}
		return null;
	}

    public final static Object runClassMethod4(Object classObject, String className, String methodName, Object[] parameters) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
    	return runClassMethod4(classObject, className, methodName, parameters, true);
	}
    /**
     * 运行指定的类的指定方法4
     * @param classObject
     * @param className
     * @param methodName
     * @param parameters
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public final static Object runClassMethod4(Object classObject, String className, String methodName, Object[] parameters, boolean caseParametersInstance) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if(stringIsNotNull(className, methodName))
		{
			Method m = getClassMethod(className, methodName, parameters, caseParametersInstance);
			if(m != null)
			{
				if(Modifier.isStatic(m.getModifiers()))
				{
					m.setAccessible(true);
		            return m.invoke(null, parameters);
				}
				else if(classObject != null)
				{
					m.setAccessible(true);
		            return m.invoke(classObject, parameters);
				}
				else
				{
                    try
                    {
                    	Class<?> c = Class.forName(className);
    	            	if(c != null)
    	            	{
    	            		classObject = c.newInstance();
    	            	}
                    } catch (Exception e)
                    {
                    }
            		if(classObject != null)
            		{
    					m.setAccessible(true);
    		            return m.invoke(classObject, parameters);
            		}
				}
			}
		}
		return null;
	}
	
	/**
	 * 运行指定的类的指定静态方法
	 * @param classAndMethodName	a.b 或 a.b() 或 a b() 或 a b
	 * @param parameters
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
    public final static Object runClassMethod2(String classAndMethodName, Object[] parameters) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
    	classAndMethodName = classAndMethodName.trim();
    	int npos = classAndMethodName.lastIndexOf('.');
    	if(npos < 0)
    		npos = classAndMethodName.lastIndexOf(' ');
    	if(npos > 0)
    	{
	    	String className = classAndMethodName.substring(0, npos).trim();
	    	String methodName = classAndMethodName.substring(npos+1).trim();
    		npos = methodName.indexOf('(');
    		if(npos > 0)
    		{
    			methodName = methodName.substring(0, npos).trim();
    			return runClassMethod(className, methodName, parameters);
    		}
    	}
    	return null;
	}
    
    /**
     * 拷贝对象
     * @param src		对象源
     * @param dest		对象目标
     */
    public final static void copyObject(Object src, Object dest)
    {
    	if(src != null && dest != null)
    	{
    		Object o = null;
        	Class<?> c = dest.getClass();

			if (c.isArray())
			{
				//无合理方案
			}
			else if (dest instanceof Map)
			{
				//无合理方案
			}
			else if (dest instanceof List)
			{
				//无合理方案
			}
			else if (dest instanceof Collection)
			{
				//无合理方案
			}
			else
			{
	        	Field[] fd = null;
	        	while(c != null)
	        	{
	            	fd = c.getDeclaredFields();
	            	if(fd != null && fd.length > 0)
	            	{
	            		for(Field f : fd)
	            		{
	            			if(!Modifier.isStatic(f.getModifiers()))
	            			{
//		            			o = getObjectByKey(src, f.getName());
	            				try
	            				{
	            					o = f.get(src);
	            				}
	            				catch(Exception e)
	            				{
	            					o = null;
	            				}
		            			if(o != null)
		            			{
		            				o = changeObjectType(o, f.getType());
			            			if(o != null)
			            			{
			    						try
			                            {
			        						f.setAccessible(true);
			                                f.set(dest, o);
			                            } catch (Exception e)
			                            {
			                            }
			            			}
		            			}
	            			}
	            		}
	            	}
	            	c = c.getSuperclass();
	        	}
			}
    	}
    }
    
    /**
     * 根据所给的对象，生成一个新的对象
     * @param <T>
     * @param src			对象源
     * @param valueType		生成的对象类
     * @return
     */
    public final static <T> T makeObject(Object src, Class<T> valueType)
    {
    	if(src != null && valueType != null && !valueType.isInterface() && !valueType.isAnnotation())
    	{
    		T t = null;
            try
            {
	            t = valueType.newInstance();
            } catch (Exception e)
            {
	            t = null;
	            e.printStackTrace();
            }
            if(t != null)
            {
            	copyObject(src, t);
            }
    		return t;
    	}
    	return null;
    }
    
    /**
     * 转换数据类型
     * @param src
     * @param cType
     * @return
     */
    public final static <T> T changeObjectType(Object src, Class<T> cType)
    {
    	if(src != null && cType != null)
    	{
	    	if (cType == Boolean.class || cType == boolean.class)
			{
				return (T)(Boolean)toBoolean(src, false);
			}
			else if (cType == Byte.class || cType == byte.class)
			{
				return (T)(Byte)Byte.parseByte(src.toString());
			}
			else if (cType == Short.class || cType == short.class)
			{
				return (T)(Short)Short.parseShort(src.toString());
			}
			else if (cType == Character.class || cType == char.class)
			{
				return (T)(Character)src.toString().charAt(0);
			}
			else if (cType == Integer.class || cType == int.class)
			{
				return (T)(Integer)toInt(src, 0);
			}
			else if (cType == Long.class || cType == long.class)
			{
				return (T)(Long)toLong(src, 0);
			}
			else if (cType == String.class)
			{
				return (T)src.toString();
			}
			else if (cType == Double.class || cType == double.class)
			{
				return (T)(Double)toDouble(src, 0);
			}
			else if (cType == Float.class || cType == float.class)
			{
				return (T)(Float)toFloat(src, 0);
			}
			else if (cType == Date.class)
			{
				return (T)(new Date(toLong(src, 0)));
			}
			else if (cType.isEnum())
			{
				int n = toInt(src, -1);
				if (n >= 0)
				{
					Object[] ol = cType.getEnumConstants();
					if (ol != null && ol.length > n)
					{
						return (T)ol[n];
					}
				}
				try
				{
					return (T)Enum.valueOf((Class)cType, src.toString());
				} catch (Exception e)
				{
				}
			}
			else if (cType.isArray())
			{
				if(src.getClass().isArray())
				{
					int len = Array.getLength(src);
					if(len > 0)
					{
						Class cc = cType.getComponentType();
						if(cc != null)
						{
							Object desk = Array.newInstance(cc, len);
							Object srco;
							for (int i = 0; i < len; i++)
							{
								srco = Array.get(src, i);
								if (srco != null)
								{
									try
									{
										if (cc == String.class)
											Array.set(desk, i, srco.toString());
										else
										{
											if (cc == Byte.class || cc == byte.class)
												Array.set(desk, i, (byte) toInt(srco, 0));
											else if (cc == Short.class || cc == short.class)
												Array.set(desk, i, (short) toInt(srco, 0));
											else if (cc == Character.class || cc == char.class)
												Array.set(desk, i, srco.toString().charAt(0));
											else if (cc == int.class || cc == Integer.class)
												Array.set(desk, i, toInt(srco, 0));
											else if (cc == long.class || cc == Long.class)
												Array.set(desk, i, toLong(srco, 0));
											else if (cc == double.class || cc == Double.class)
												Array.set(desk, i, toDouble(srco, 0));
											else if (cc == float.class || cc == Float.class)
												Array.set(desk, i, toFloat(srco, 0));
											else if (cc == boolean.class || cc == Boolean.class)
												Array.set(desk, i, toBoolean(srco, false));
										}
									} catch (Exception e)
									{
									}
								}
							}
							return (T)desk;
						}
					}
				}
				return null;
			}
			else
			{
				return (T)makeObject(src, cType);
			}
    	}
    	return (T)src;
    }
    
    /**
     * 判断某类是否继承某个接口
     * @param c
     * @param interfaceName
     * @return
     */
    public final static boolean isInterfaceFrom(Class c, String interfaceName)
    {
    	while(c != null)
    	{
        	Class[] ccl = c.getInterfaces();
    		if (ccl != null && ccl.length > 0)
    		{
    			for (Class<?> cc : ccl)
    			{
    				if (cc.getName().equals(interfaceName))
    				{
    					return true;
    				}
    			}
    		}
    		c = c.getSuperclass();
    	}
    	return false;
    }
    
    
	
	/**
	 * 以缓存方式执行某方法
	 * @param <T>
	 * @param classObject
	 * @param method
	 * @param valueType
	 * @param args
	 * @return
	 */
	public static <T> T cacheRunMethod(Object classObject, String method, long cacheTime, Class<T> valueType, Object ... args)
	{
		return null;
	}
	
	
	/**
	 * 两个字节的字节数组转换为int
	 * @param b
	 * @return
	 */
	public static int byteToInt2(byte[] b) {
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for (int i = 0; i < 2; i++) {
			n <<= 8;
			temp = b[i] & mask;
			n |= temp;
		}
		return n;
	}
}
