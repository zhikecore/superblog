package com.zhike.blogadmin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

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
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * old 分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("created", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modified", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modified", LocalDateTime.now(), metaObject);
    }
}
