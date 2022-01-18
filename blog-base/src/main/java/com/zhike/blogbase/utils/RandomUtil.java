package com.zhike.blogbase.utils;

import java.util.Random;

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
 * RandomUtil at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class RandomUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwsyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTECHAR = "abcdefghijklmnopqrstuvwsyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 *
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// System.out.println(random.nextInt(5));
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 *
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTECHAR.charAt(random.nextInt(LETTECHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * @Title: generateLowerString
	 * @Description: 返回一个定长的随机纯大写字母字符串(只包含字母)
	 * @param @param  length
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws @author Jason
	 * @date 2016年11月18日 下午8:36:49
	 * @version V1.0
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含字母)
	 * 
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length 字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num       数字
	 * @param fixdlenth 字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlength) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlength - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlength - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlength + "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 每次生成的len位数都不相同
	 *
	 * @param param
	 * @return 定长的数字
	 */
	public static int getNotSimple(int[] param, int len) {
		Random random = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = random.nextInt(i);
			System.out.println("index:" + index);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
			System.out.println(result);
		}
		return result;
	}
}
