package com.zhike.blogwebapi.controller;

//import com.zhike.blogpojo.DO.Article;
//import com.zhike.blogservice.ArticleService;

import com.zhike.blogbase.annotation.EnableResponseResult;
import com.zhike.blogbase.annotation.IgnoreResponseResult;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.result.ResponseResult;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogpojo.Query.UserRequest;
import com.zhike.blogpojo.VO.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
 * UserController at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Api(tags = "用户操作")
@RestController
@AllArgsConstructor
@EnableResponseResult
@IgnoreResponseResult
public class UserController {
//public class UserController extends  BaseController {
//public class UserController {

//    @Autowired
//    private ArticleService articleService;

    //@GetMapping("/info")
//    @ApiImplicitParam(name = "id", value = "ID", required = true)
//    @ApiOperation(value = "获取用户信息")
//    @GetMapping("/info")
//    public Boolean info(String id){
//
//        if(StringUtil.isEmpty(id))
//            throw  new BizException("id不能为空,请检查!","1");
//
//        //List<Article> articleList=articleService.getCarousels();
//        return true;
//    }

    @ApiImplicitParam(name = "id", value = "ID", required = true)
    @ApiOperation(value = "获取单个用户信息")
    @GetMapping("/getUser")
    public UserVO getUser(@RequestParam(value = "id") String id) {

        if (StringUtil.isEmpty(id))
            throw new BizException("id不能为空,请检查!", "1");

        //List<Article> articleList=articleService.getCarousels();

        UserVO vo = new UserVO();
        vo.setId(1);
        //vo.setUsername("LuckyHu");
        vo.setAccount("LuckyHu");
        return vo;
    }

    @ApiImplicitParam(name = "id", value = "ID", required = true)
    @ApiOperation(value = "获取用户名称")
    @GetMapping("/getUserName")
    public String getUserName(@RequestParam(value = "id") String id) {

        if (StringUtil.isEmpty(id))
            throw new BizException("id不能为空,请检查!", "1");

        //List<Article> articleList=articleService.getCarousels();

        return "LuckyHu";
    }

    /**
     * 测试
     */
    @ApiOperation(value = "新增用户")
    @PostMapping("createUser")
    public Object test(@Validated @RequestBody UserRequest user) {
        return "hello world";
    }
}
