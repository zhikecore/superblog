package com.zhike.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
 * DefaultViewConfig at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Slf4j
//@Configuration
public class DefaultViewConfig extends WebMvcConfigurationSupport {
    /**
     * 添加主页方法
     *
     * @param registry 主页注册器
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        //System.out.println("设置了主页");
        log.info("access to DefaultViewConfig. 设置你Default.index 为主页!");
        //设置主页
        registry.addViewController("/").setViewName("/default/index");
        //设置优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        //将主页注册器添加到视图控制器中
        super.addViewControllers(registry);
    }
}
