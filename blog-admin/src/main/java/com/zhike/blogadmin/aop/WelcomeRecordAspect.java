package com.zhike.blogadmin.aop;

import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogbase.annotation.WelcomeRecord;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
 * WelcomeRecordAspect at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Aspect
@Component
@Slf4j
public class WelcomeRecordAspect {

    @Pointcut("@annotation(com.zhike.blogbase.annotation.WelcomeRecord)")
    public void performance(){}

    @Before("performance()")
    public void before() {
        log.debug("2");
        System.out.print(2);
    }

    @AfterReturning("performance()")
    public void afterReturn(){
        log.debug("3");
        System.out.print(3);
    }

    @After("performance()")
    public void after(){
        log.debug("4");
        System.out.print(4);
    }
    @Around("performance()")
    public Object around(ProceedingJoinPoint joinpoint){

        Object obj=null;
        try
        {
            log.debug("5");
            System.out.print(5);
            obj=joinpoint.proceed();

            System.out.print(6);
            log.debug("6");
        } catch(Throwable e){
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private WelcomeRecord  getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(WelcomeRecord.class);
        }
        return null;
    }
}