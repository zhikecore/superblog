package com.zhike.blogwebapi.controller;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2020/12/13
 * Time: 17:59
 * Description: No Description
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * IndexController at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Api(tags = "首页模块")
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private Environment env;

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){

        log.info("向客人问好:call sayhi");
        log.error("call sayhi error~");
        log.error("print name:"+name);

        return ResponseEntity.ok("Hi:"+name);
    }


//    @ApiImplicitParam(name = "name", value = "测试", required = true)
//    @ApiOperation(value = "测试")
    @GetMapping("/testProfile")
    public String testProfile(){
        return env.getProperty("profile");
    }
}
