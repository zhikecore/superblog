package com.zhike.blogadmin.controller;

import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.DO.Adminuser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserManager userManager;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("ddd");
        Adminuser adminuser =userManager.findByAccount("luckyhu");
        return "hello world!";
    }
}

