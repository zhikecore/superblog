package com.zhike.aop;

import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ArticleParamsConvertAspect {

    /**
     * 此处定义切点是注解方法,这里使用注解的方式。
     * 也可以使用aop最原始的支撑包名的方式
     */
    @Pointcut("@annotation(com.zhike.blogbase.annotation.ArticleParamsConvert)")
    public void articleParamsConvert(){}

//    @Before("BrokerAspect()")
//    public void doBefore(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            if (arg instanceof Model) {
//                Model model = (Model) arg;
//                model.addAttribute("test", "存放公共参数");
//            }
//        }
//    }

    @Around("articleParamsConvert()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){
        //Object[] sourceObject =proceedingJoinPoint.getArgs();
        //System.out.println("before==getArgs:"+ JsonUtil.toJson(proceedingJoinPoint.getArgs()));
//        for (Object arg : sourceObject) {
//            System.out.println(arg);
//        }

        Object[] args = proceedingJoinPoint.getArgs();
        //System.out.println("before==getArgs:"+ JsonUtil.toJson(args));
        //修改指定参数
        for (Object arg : args) {
            if (arg instanceof ArticleByUserIdBo){
                ArticleByUserIdBo bo = (ArticleByUserIdBo) arg;
                bo.setUserid(1);
                arg=bo;
            }
        }

        try {
            //System.out.println("after==getArgs:"+ JsonUtil.toJson(args));
            proceedingJoinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Before("articleParamsConvert()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "articleParamsConvert()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("articleParamsConvert()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("articleParamsConvert()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }
}

