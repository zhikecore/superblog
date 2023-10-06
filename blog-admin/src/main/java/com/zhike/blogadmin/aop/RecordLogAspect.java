package com.zhike.blogadmin.aop;

import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogbase.utils.HttpContextUtil;
import com.zhike.blogbase.utils.IpUtil;
import com.zhike.blogpojo.DO.OperationRecordLog;
import com.zhike.blogservice.RecordLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

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
 * RecordLogAspect at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Aspect
@Component
public class RecordLogAspect {

    @Autowired
    private RecordLogService recordLogService;

    /**
     * 此处定义切点是注解方法,这里使用注解的方式。
     * 也可以使用aop最原始的支撑包名的方式
     */
    @Pointcut("@annotation(com.zhike.blogbase.annotation.RecordLog)")
    public void recordLog(){}

    /**
     * 环绕增强，在这里进行日志操作
     */
    @Around("recordLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{

        Object res = null;
        long time = System.currentTimeMillis();
        try {
            //方法继续执行
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addRecordnLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addRecordnLog(JoinPoint joinPoint, Object res, long time){
        //所属类的class等信息
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();

        // 获取到 Method 对象，可以用于获取方法上面的注解
        Method method = signature.getMethod();

        //获取注解对象，取到注解里定义的字段
        RecordLog annotation = signature.getMethod().getAnnotation(RecordLog.class);
        if(annotation != null) {

            String funModule = annotation.funModule();
            String funMethod=annotation.funMethod();

            //保存日志
            // 获取 resquest 对象
            HttpServletRequest httpServletRequest = HttpContextUtil.getHttpServletRequest();
            OperationRecordLog recordLog=new OperationRecordLog();
            recordLog.setFun_module(funModule);
            recordLog.setFun_method(funMethod);
            //recordLog.setFunParams(jsonParms);
            recordLog.setIp(IpUtil.getIpAddress(httpServletRequest));
            recordLog.setCreateTime(new Date());
            recordLog.setCost_time(time);

            // 请求方法参数名
            Object[] args = joinPoint.getArgs();

            // 获取方法的参数名
            LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);

            // 拼接： 参数名：参数值
            if (args != null && parameterNames != null) {
                String params = "";
                for (int i = 0 ; i < args.length; i++) {
                    params += "  " + parameterNames[i] + ": " + args[i];
                }
                recordLog.setFun_params(params);
            }

            recordLogService.save(recordLog);
        }
    }

    @Before("recordLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "recordLog()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("recordLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("recordLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private RecordLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(RecordLog.class);
        }
        return null;
    }
}
