package com.zhike.controller;

import com.zhike.blogbase.result.PagedResult;
//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 * TestController at 2021/1/9 13:46,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController  {

    @Resource
    private ArticleService articleService;

    @Resource
    private RedisUtils redisUtils;

    @RequestMapping("/index")//主页
    public String index(){

        String cacheKey="im:session:118962";
        if(StringUtils.isNotEmpty(cacheKey))
        {
            Object object = redisUtils.get(cacheKey);
            //System.out.println("cacheValue:"+cacheValue);
            String json2 = object.toString();
            System.out.println("json2"+json2);
        }
        return "/test/index";
    }

    @RequestMapping("/start")
    public String start(){
        return "/test/start";
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        // 设置模板文件中使用的参数
        model.addAttribute("title", "52Interview");
        return "/test/hello";
    }

    @RequestMapping("/startWithWebJar")
    public String startWithWebJar(){
        return "/test/startWithWebJar";
    }
}
