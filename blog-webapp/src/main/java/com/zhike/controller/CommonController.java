package com.zhike.controller;

import com.zhike.blogpojo.VO.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
 * CommonController at 2022/7/10 22:22,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/success")
    public ModelAndView doSuccess(){
        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("/common/success");
        return modelAndView;
    }

    @RequestMapping("/failure")
    public ModelAndView doFailure(){
        ModelAndView modelAndView = new ModelAndView("/common/failure");
        return modelAndView;
    }
}
