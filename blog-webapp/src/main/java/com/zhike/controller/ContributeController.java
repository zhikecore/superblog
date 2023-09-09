package com.zhike.controller;

import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogbase.annotation.IgnoreAuth;
import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogbase.annotation.WelcomeRecord;
import com.zhike.blogpojo.VO.Chat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
 * ContributeController at 2021/3/15 22:25,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/contribute")
@Slf4j
@EnableAuth
public class ContributeController {

    @RequestMapping("/index")//主页
    @IgnoreAuth
    public String index(){
        return "contribute/index";
    }

    @WelcomeRecord()
    @RequestMapping("/say")
    @IgnoreAuth
    public String say()
    {
        System.out.print(1);
        log.debug("1");
        return "hello";
    }
}
