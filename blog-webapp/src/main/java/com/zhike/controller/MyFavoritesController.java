package com.zhike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogbase.result.SuperPage;
import com.zhike.blogbase.utils.ServletUtil;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogpojo.VO.CarouselVO;
import com.zhike.blogservice.MyFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright (C) 2023  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * ArticleController at 2023/09/09 11:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/myFavorites")
@Slf4j
@EnableAuth
public class MyFavoritesController {

    @Resource private MyFavoriteService myFavoriteService;
    @Resource
    private UserManager userManager;
    @RequestMapping("/index")
    public ModelAndView Index(
            HttpServletRequest request,
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        ArticleAO ao = new ArticleAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        HttpSession session =  ServletUtil.getRequest().getSession();
        String account = (String) session.getAttribute("aname");
        Adminuser loginUser=userManager.findByAccount(account);

        ModelAndView modelAndView = new ModelAndView("myFavorites/index");
        ArticleByUserIdBo bo=new ArticleByUserIdBo();
        bo.setUserId(loginUser.getId());
        SuperPage<ArticleVO> page=myFavoriteService.listArticles(bo);

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);

        return modelAndView;

    }

}
