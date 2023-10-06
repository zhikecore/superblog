package com.zhike.blogadmin.config;

import com.zhike.Interceptor.AuthHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
 * WebMvcConfig at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册登录拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(new AuthHandlerInterceptor()).addPathPatterns("/**")//所有请求路径都被拦截
                .excludePathPatterns(//不拦截的请求路径
                        "/",
                        "/account/login",
                        "/account/gologin",
                        "/account/register",
                        "/common/success",
                        "/common/failure",
                        "/assets/**",
                        "/css/**",
                        "/font/**",
                        "/font-awesome4.0.3/**",
                        "/js/**",
                        "/images/**",
                        "/avatars/**",
                        "/img/**");
    }
}
