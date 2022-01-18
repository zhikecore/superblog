package com.zhike.controller;

//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogbase.annotation.IgnoreAuth;
import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.zhike.blogbase.annotation.EnableAuth;

import javax.annotation.Resource;

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
 * ArticleController at 2021/1/9 11:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Controller
@RequestMapping("/article")
@Slf4j
@EnableAuth
public class ArticleController {

    @Resource
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleManager articleManager;

    @RecordLog(funModule="article",funMethod="index")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable long id) {
        log.debug("debug:article index...");

        Adminuser adminuser=articleManager.findById(1);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("article/detail");
        ArticleVO articleVO=articleService.findById(id);
        modelAndView.addObject("article",articleVO);
        return modelAndView;
    }

//    @RequestMapping(value = "detail", method = RequestMethod.GET)
//    public String detail(Models model){
//
//        ArticleVO articleVO=articleService.findById(2);
//        model.addAttribute("article",articleVO);
//        return "/article/detail";
//    }
}
