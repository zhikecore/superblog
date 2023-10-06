package com.zhike.blogadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Slf4j
public class DefaultController {
    @RequestMapping("/")
    public ModelAndView Index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
