package com.zhike.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/4/11
 * Time: 10:36
 * Description: No Description
 */
@Controller
@RequestMapping("/default")
@Slf4j
public class DefaultController {

    @RequestMapping("/index")//主页
    public String index(){

        return "/default/index";
    }
}
