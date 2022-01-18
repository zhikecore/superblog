package com.zhike.blogbase.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

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
 * BeanHelper at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Slf4j
public class BeanHelper {

	/**
	 * 转换单个对象 浅复制,用于无嵌套对象的拷贝
	 */
	public static <S, T> T convertBean(S source, Supplier<T> supplier) {
		if (source == null) {
			return null;
		}
		T target = supplier.get();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	/**
	 * 转换对象列表 浅复制,用于无嵌套对象的拷贝
	 */
	public static <S, T> List<T> convertBeans(List<S> source, Supplier<T> supplier) {
		if (CollectionUtils.isEmpty(source)) {
			return Collections.emptyList();
		}
		List<T> target = new ArrayList<>(source.size());
		for (S aSource : source) {
			T targetEntity = supplier.get();
			convertBean(aSource, targetEntity);
			target.add(targetEntity);
		}
		return target;
	}

	/**
	 * 深复制单个对象 深复制,用于有嵌套对象的拷贝
	 */
	public static <T> T deepCopy(Object obj, Class<T> clazz) {
		if (obj == null || clazz == null) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(obj), clazz);
	}

	/**
	 * 深转换对象列表 深复制,用于有嵌套对象的拷贝
	 */
	public static <T> List<T> deepCopys(Object obj, Class<T> clazz) {
		if (obj == null || clazz == null) {
			return null;
		}
		return JSON.parseArray(JSON.toJSONString(obj), clazz);
	}

	private static <S, T> void convertBean(S source, T target) {
		BeanUtils.copyProperties(source, target);
	}

	/**
	 * 判断两个有相同属性的非空字段值是否都相等 如果target对象某个字段为null,则默认不匹配该字段
	 */
	public static <T, S> boolean isFieldEquals(T target, S source) {
		try {
			Field[] fields = target.getClass().getDeclaredFields();
			Field field;
			String fieldName;
			Class clzT = target.getClass();
			Class clzS = source.getClass();
			String getMethodName;
			Object targetValue;
			Object sourceValue;

			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				fieldName = field.getName();
				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				targetValue = clzT.getMethod(getMethodName, new Class[] {}).invoke(target, new Object[] {});
				sourceValue = clzS.getMethod(getMethodName, new Class[] {}).invoke(source, new Object[] {});
				if (targetValue == null) {
					return true;
				}
				if ((targetValue == null && sourceValue != null) || (targetValue != null && sourceValue == null)) {
					return false;
				}
				if (!targetValue.equals(sourceValue)) {
					return false;
				}
			}
		} catch (Exception e) {
			log.error("BeanHelper.isFieldEquals error", e);
			return false;
		}
		return true;
	}

}
