package com.zhike.blogbase.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Copyright (C) 2022  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * DateUtils at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class DateUtils {

	public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

	public static final String YMD_HMS_SS = "yyyy-MM-dd HH:mm:ss:SSS";

	public static final String YMD = "yyyy-MM-dd";

	public static final String HMS = "HH:mm:ss";

	public static Date getDateYMD(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(YMD);
		String str = sdf.format(date);
		return sdf.parse(str);
	}

	public static Date getDateHMS(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(HMS);
		String str = sdf.format(date);
		return sdf.parse(str);
	}

	public static Date getDateYMDHMS(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS_SS);
		String str = sdf.format(date);
		return sdf.parse(str);
	}

	public static String getTimeUUID() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS_SS);
		String str = sdf.format(date);
		str = str.replaceAll("-", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll(" ", "");
		return str;
	}

	public static String getTimeHMSUUID() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS);
		String str = sdf.format(date);
		str = str.replaceAll("-", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll(" ", "");
		return str;
	}

	public static Date getDateHMSFromStr(String time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS);
		return sdf.parse(time);
	}

	/**
	 * 时间格式化成字符串
	 *
	 * @param date    Date
	 * @param pattern 如果为空，则为Constants.YMD_HMS
	 * @return
	 * @throws ParseException
	 */
	public static String dateFormat(Date date, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = YMD_HMS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS);
		return sdf.format(date);
	}

	/**
	 * 字符串解析成时间对象
	 *
	 * @param dateTimeString String
	 * @param pattern        如果为空，则为Constants.YMD_HMS
	 * @return
	 * @throws ParseException
	 */
	public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
		if (StringUtils.isEmpty(pattern)) {
			pattern = YMD_HMS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateTimeString);
	}

	/**
	 * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
	 *
	 * @param myDate      时间
	 * @param compareDate 要比较的时间
	 * @return int
	 */
	public static int dateCompare(Date myDate, Date compareDate) {
		Calendar myCal = Calendar.getInstance();
		Calendar compareCal = Calendar.getInstance();
		myCal.setTime(myDate);
		compareCal.setTime(compareDate);
		return myCal.compareTo(compareCal);
	}

	/**
	 * 将日期时间格式成日期对象，和dateParse互用
	 *
	 * @param dateTime Date
	 * @return Date
	 */
	public static Date dateTimeToDate(Date dateTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 时间加减天数
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateAddDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 时间加减小时
	 *
	 * @param startDate 要处理的时间，Null则为当前时间
	 * @param hours     加减的小时
	 * @return Date
	 */
	public static Date dateAddHours(Date startDate, int hours) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
		return c.getTime();
	}

	/**
	 * 时间加减分钟
	 *
	 * @param startDate 要处理的时间，Null则为当前时间
	 * @param minutes   加减的分钟
	 * @return
	 */
	public static Date dateAddMinutes(Date startDate, int minutes) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
		return c.getTime();
	}

	/**
	 * 根据日期获取当前日期的开始时间和结束时间（即00:00:000-23:59:999）
	 *
	 * @param date
	 * @return index:0 开始时间 index:1 结束时间
	 */
	public static Date[] getTimes(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date beginTime = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		Date endTime = calendar.getTime();

		return new Date[] { beginTime, endTime };
	}

	/**
	 * 获取上个月的开始时间以及结束时间（第一天 00 点 到 最后一天 23点：59:59）
	 */
	public static Date[] getPreMonth() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date beginTime = calendar.getTime();
		calendar.clear();
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date endTime = calendar.getTime();
		return new Date[] { beginTime, endTime };
	}

	/**
	 * 获取当月第一天与最后一天的数据
	 *
	 * @param time
	 * @return
	 */
	public static Date[] getMonth(String time) {
		Calendar calendar = Calendar.getInstance();
		if (StringUtils.isEmpty(time)) {
			// 获取当月第一天与最后一天
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date beginTime = calendar.getTime();
			calendar.clear();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 0);
			Date endTime = calendar.getTime();
			return new Date[] { beginTime, endTime };
		}
		return null;
	}

	/**
	 * LocalDate转Date
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDateTime转Date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime)) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Date转LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Date转LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * @return 设置过期时间第二天0点
	 */
	public static Date getExpireDateForNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

}
