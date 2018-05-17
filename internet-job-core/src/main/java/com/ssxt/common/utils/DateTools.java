package com.ssxt.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
* @ClassName: DateTools 
* @Description: 日期时间工具类
* @author Nick
* @date 2016年4月19日 上午8:45:43 
*  
*/
public class DateTools {
	// 日期时间默认格式
	public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static Calendar setTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/** 
	* @Description: set seconds to be 00
	* @param date
	* @return Date
	*/
	public static Date date(Date date) {
		Calendar cal = setTime(date);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/** 
	* @Description: get time of next n hours
	* @param date
	* @param n
	* @return Date
	*/
	public static Date getNextNHours(Date date, int n) {
		Calendar cal = setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, n);
		return cal.getTime();
	}
	
	/** 
	* @Description: get next or before serval minutes time;
	* 	if n > 0, get next n minutes time;
	* 	if n < 0, return before |n| minutes time;
	* @param date
	* @param n
	* @return Date
	*/
	public static Date getNextNMins(Date date, int n) {
		Calendar cal = setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}
	
	/**
	 * @Description: 日期初始化
	 * @param Date
	 * @param String
	 * @return String
	 */
	public static String DateFormate(Date date, String... pattern) {
		String timeStr = "";
		if (pattern != null && pattern.length != 0) {
			timeStr = new SimpleDateFormat(pattern[0]).format(date);
			return timeStr;
		} else {
			timeStr = new SimpleDateFormat(DateTools.DEFAULT_PATTERN).format(date);
		}
		return timeStr;
	}
	
	/** 
	* @Description: 将String类型的时间转换成Date型
	* @param String dateStr
	* @param String pattern
	* @return	Date
	* @throws ParseException 
	*/
	public static Date stringDateFormate(String dateStr, String pattern) throws ParseException {
		return pattern == null ? new SimpleDateFormat(pattern).parse(DateTools.DEFAULT_PATTERN)
				: new SimpleDateFormat(pattern).parse(dateStr);
	}
	
	/** 
	* @Description: 比较两个时间大小
	* @param Date date1
	* @param Date date2
	* @return  int 
	*/
	public static int DateCompare(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		return c1.compareTo(c2);
	}
	
	/**
	 * @Description: set second 0
	 * @param date
	 * @return Date
	 */
	public static Date clearSeconds(Date date) {
		Calendar cal = setTime(date);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/** 
	* @Description: get minute of date
	* @param date
	* @return int
	*/
	public static int getMinutes(Date date) {
		Calendar cal = setTime(date);
		return cal.get(Calendar.MINUTE);
	}
	
	
	public static Date setMinutes(Date date, int minutes) {
		Calendar cal = setTime(date);
		cal.set(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
	/** 
	* @Description: set minute of date
	* @param date
	* @param minute
	* @return Date
	* @throws 
	*/
	public static Date setSeconds(Date date, int seconds) {
		Calendar cal = setTime(date);
		cal.set(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	public static void main(String[]  args) throws ParseException {
		/*StringBuffer buf = new StringBuffer();
		buf.append("323232#3454354#453453#");
		String result = buf.substring(0, buf.length() - 1);
		System.out.println(result);
		String dateStr1 = "20160419234512";
		String dateStr2 = "20160419235012";
		Date date1 = stringDateFormate(dateStr1, DateTools.DEFAULT_PATTERN);
		Date date2 = stringDateFormate(dateStr2, DateTools.DEFAULT_PATTERN);
		System.out.println(DateCompare(date1, date2));*/
		/*Date date = stringDateFormate(dateStr, "yyyyMMddHHmmss");
		String after30Mins = DateFormate(getNext30Mins(date));
		System.out.println(after30Mins);*/
		/*Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-01-01 00:29:59");
		System.out.println(DateTools.DateFormate(DateTools.clearSeconds(DateTools.get8HoursBefore(date))));*/
		Date imgShowTime = stringDateFormate("2016-02-28 12:00:12", "yyyy-MM-dd HH:mm:ss");
		int minutes = DateTools.getMinutes(imgShowTime);
		minutes = minutes - (minutes % 6);
		imgShowTime = DateTools.setMinutes(imgShowTime, minutes);
		imgShowTime = DateTools.getNextNMins(imgShowTime, -120);
		System.out.println(DateFormate(imgShowTime));
		/*Date date = new Date();
		System.out.println(DateFormate(date));
		int minute = getMinutes(date);
		minute = minute - (minute % 6);
		date = setMinutes(date, minute);
		System.out.println(DateFormate(date));
		date = getNextNMins(date, -120);
		System.out.println(DateFormate(date));*/
	}
}
