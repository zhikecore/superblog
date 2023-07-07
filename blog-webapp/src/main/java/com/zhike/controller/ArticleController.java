package com.zhike.controller;

//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogbase.constant.RedisKeyConstant;
import com.zhike.blogbase.enums.ResponseCode;
import com.zhike.blogbase.result.ResponseResult;
import com.zhike.blogbase.utils.CollectionUtil;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogbase.utils.ServletUtil;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DTO.input.ArticleLikeDto;
import com.zhike.blogpojo.DTO.input.ArticleUnLikeDto;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.zhike.blogbase.annotation.EnableAuth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
 * ArticleController at 2021/1/9 11:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Controller
@RequestMapping("/article")
@Slf4j
@EnableAuth
public class ArticleController {

    @Resource
    @Autowired
    private ArticleService articleService;

    @Resource
    private ArticleManager articleManager;

    @Resource
    private RedisUtils redisUtils;

    @RecordLog(funModule="article",funMethod="index")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable Integer id) {
        log.debug("debug:article index...");
        HttpSession session =  ServletUtil.getRequest().getSession();
        String account = (String) session.getAttribute("aname");
        Adminuser loginUser=articleManager.findByAccount(account);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("article/detail");
        ArticleVO articleVO=articleService.findById(id);
        //是否点赞
        Boolean isLiked=isLiked(id,loginUser.getId());
        articleVO.setIsLiked(isLiked);

        modelAndView.addObject("article",articleVO);
        return modelAndView;
    }

    private  Boolean  isLiked(Integer articleId,Integer loginUserId)
    {
        Boolean isLiked=false;
        try
        {
            String key= RedisKeyConstant.ARTICLE_LIKED_USERS;
            String itemKey=articleId.toString();
            String articleLikedResult = (String) redisUtils.hget(key,itemKey);
            Set<Integer> likePostIdSet =articleLikedResult==null?new HashSet<>(): JsonUtil.deserializeToSet(articleLikedResult,Integer.class);

            if(CollectionUtil.isEmpty(likePostIdSet) )
            {
                return  isLiked;
            }

            if(likePostIdSet.contains(loginUserId))
            {
                isLiked=true;
            }

        }catch (Exception ex)
        {
            log.error("获取我的点赞状态失败!error:"+ex.getMessage());
        }
        return isLiked;
    }

}
