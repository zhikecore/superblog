package com.zhike.blogadmin.Interceptor;

import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogbase.annotation.IgnoreAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * AuthHandlerInterceptor at 2021/1/3 18:36,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class AuthHandlerInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /***
         * controller权限控制
         */
        // 如果请求的不是方法 则直接跳过当前拦截器
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod  handlerMethod = (HandlerMethod) handler;

        // 获取当前请求的方法
        Method method = handlerMethod.getMethod();

        // 获取方法上注解
        EnableAuth auth = method.getAnnotation(EnableAuth.class);//需要验证
        IgnoreAuth ignoreAuth=method.getAnnotation(IgnoreAuth.class);//跳过验证

        //如果跳过验证,则返回
        if(ignoreAuth!=null)
            return  true;

        // 如果方法没有注解,则去类上去获取 如果方法有则使用方法的注解,方法的注解优先级高于类的注解
        if (auth == null) {
            auth = handlerMethod.getBeanType().getAnnotation(EnableAuth.class);
        }

        // 如果没有Auth 注解 则跳过鉴权
        if (auth == null) {
            return true;
        }
        Object username = request.getSession().getAttribute("aname");
        if (username != null) {
            //已登录放行
            return true;
        } else {
            // 未登陆，返回登录页面
            request.setAttribute("msg","无权限请先登录");
            request.getRequestDispatcher("/account/login").forward(request, response);
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

