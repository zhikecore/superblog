package com.zhike.blogadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("ddd");
        return "hello world!";
    }
}

