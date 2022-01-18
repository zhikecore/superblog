package com.zhike.blogbase.utils;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * CollectionUtil at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class CollectionUtil {

    private static final String SEPARATOR_COMMA = ",";

    public static Set<String> getSetBySplit(String content){
        return getSetBySplit(content, SEPARATOR_COMMA);
    }

    public static Set<String> getSetBySplit(String content, String separator){
        Set<String> set = new HashSet<>();
        if(StringUtils.isEmpty(content)){
            return set;
        }
        for (String item : content.split(separator)) {
            set.add(item);
        }
        return set;
    }
    
    
    /**
     * 判断collection是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        //return CollectionUtils.isEmpty(collection);
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Collection是否非空
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        //return MapUtils.isEmpty(map);
        return map == null || map.isEmpty();
    }

    /**
     * 判断map是否非
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }
        
}
