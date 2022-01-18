package com.zhike.filter;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

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
 * SensitiveWordFilter at 2022/1/16 18:50,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@WebFilter(filterName = "SensitiveWordFilter",urlPatterns = {"/*"})
public class SensitiveWordFilter implements Filter {
    ArrayList<String> list = new ArrayList<String>();

    /**
     * @param
     * @method 初始化我们的过滤词的配置
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            String line;
            //读取我们的过滤配置文件
            File resource = ResourceUtils.getFile("classpath:static/sensitive.txt");
            BufferedReader sensitivewords = new BufferedReader(new FileReader(resource));
            //把读取到的信息，存放到list中
            while ((line = sensitivewords.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param
     * @method 这里只针对request中的getParameter方法进行敏感词过滤
     */
    /*
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //jdk动态代理的使用，我写过这个的文章哦
        HttpServletRequest request = (HttpServletRequest) Proxy.newProxyInstance(
                req.getClass().getClassLoader(),
                req.getClass().getInterfaces(),
                new  InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //拦截getParameter方法
                        if ("getParameter".equals(method.getName())) {
                            //调用getParameter方法的到value
                            String values = (String) method.invoke(req, args);
                            //如果有getParameter的调用，则会进行过滤并且返回经过处理的返回值，其他的直接放行
                            if (values != null) {
                                //与我们初始化的list比较是否要过滤
                                for (String s : list) {
                                    if (values.contains(s)) {
                                        values = values.replaceAll(s, "*");
                                        System.out.println("过滤成功");
                                    }
                                }
                            }
                            return values;
                        } else {
                            return method.invoke(req, args);
                        }
                    }

                });
        //放行
        filterChain.doFilter(request, resp);

    }

    */

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理对象，增强getParameter
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是不是getParameter方法
                if (method.getName().equals("getParameter")){
                    //获取返回值
                    String value = (String)method.invoke(req, args);
                    if (value != null){
                        for (String s : list){
                            if (value.contains(s)){
                                value = value.replaceAll(s,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req, resp);
    }


    @Override
    public void destroy() {

    }
}

