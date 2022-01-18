package com.zhike.blogbase.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Bean对象与Map相互转换
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
 * IpUtil at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class JavaBean2Map {

    public static Map<String, Object> convertBean(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field fields[] = bean.getClass().getDeclaredFields();
        try {
            Field.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                map.put(fields[i].getName(), fields[i].get(bean));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Object convertMap(Class<?> type, Map<String, Object> map) {
        Object ob = null;
        try {
            if (map != null && map.size() > 0) {
                ob = type.newInstance();
                Field fields[] = type.getDeclaredFields();
                Field.setAccessible(fields, true);
                for (int i = 0; i < fields.length; i++) {
                    if (map.containsKey(fields[i].getName())) {
                        fields[i].set(ob, map.get(fields[i].getName()));
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return ob;
    }

    public static Map convertBean1(Object bean) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static Object convertMap1(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object obj = type.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
}