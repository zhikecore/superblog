package com.zhike.blogadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Springfox-swagger2 配置
 * Copyright (C) 2023  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * WebMvcConfig at 2023/09/30 09:30,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位，使得先让 json 转换器处理返回值，这样 String转换器就处理不了了。
        //converters.add(0, new MappingJackson2CborHttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，不使用String类型的转换器
        //converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}
