package com.zhike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.AO.ArticleTypeAO;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DO.ArticleType;
import com.zhike.blogpojo.VO.ArticleTypeVO;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 * CategoryController at 2021/2/12 17:27,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/category")
@Slf4j
@EnableAuth
public class CategoryController {

    @Resource
    private ArticleTypeService articleTypeService;

    //@Resource
    //private ArticleService articleService;

//    @Autowired
//    private ArticleManager articleManager;

    @RequestMapping("/index")
    public ModelAndView Index(
            ArticleType articleType,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {

        //封装值到AO
        ArticleTypeAO ao=new ArticleTypeAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("category/index");
        IPage<ArticleTypeVO> page=articleTypeService.searchByPage(ao);

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("articletype",articleType);
        return modelAndView;

    }

    @RequestMapping("/detail")
    public ModelAndView Detail(
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "categoryId",defaultValue = "0") int categoryId) {

        //封装值到AO
        ArticleAO ao = new ArticleAO();
        ao.setArticleTypeId(categoryId);
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("category/detail");
        //IPage<ArticleVO> page= articleManager.searchByPage(pageNum,pageSize,categoryId);
        IPage<ArticleVO> page=articleTypeService.getArticlesByTypeId(pageNum,pageSize,categoryId);

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);
        modelAndView.addObject("articleTypeId",categoryId);
        return modelAndView;

    }


    /*@RequestMapping("/detail")
    public ModelAndView Index(
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "categoryId",defaultValue = "0") int categoryId) {

        log.debug("debug:category detail...");
        log.info("info: access to category index 为主页!");
        log.error("error:category index...");


        //封装值到AO
        ArticleAO ao = new ArticleAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("category/detail");
        IPage<ArticleVO> page=articleService.searchByPage(pageNum,pageSize,article.getTitle());

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);
        return modelAndView;
    }*/

}
