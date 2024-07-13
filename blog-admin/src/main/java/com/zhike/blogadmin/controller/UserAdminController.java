package com.zhike.blogadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.VO.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C) 2024  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * ArticleController at 2024/03/09 21:18,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Controller
@RequestMapping("/user")
@Slf4j
@EnableAuth
public class UserAdminController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView Index(
            HttpServletRequest request,
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        //封装值到AO
        ArticleAO ao = new ArticleAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("article/index");
        /*IPage<ArticleVO> page=articleService.searchByPage(pageNum,pageSize,article.getTitle());

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);;*/

        return modelAndView;

    }
}
