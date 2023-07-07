package com.zhike.controller;

import com.zhike.blogbase.result.PagedResult;
//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

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
 * TestController at 2021/1/9 13:46,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController  {

    @Resource
    private ArticleService articleService;

    @Resource
    private RedisUtils redisUtils;

    @RequestMapping("/index")//主页
    public String index(){

//        String cacheKey="im:session:118962";
//        if(StringUtils.isNotEmpty(cacheKey))
//        {
//            Object object = redisUtils.get(cacheKey);
//            //System.out.println("cacheValue:"+cacheValue);
//            String json2 = object.toString();
//            System.out.println("json2"+json2);
//        }

        ArticleByUserIdBo bo=new ArticleByUserIdBo();
        bo.setUsername("jeffreyHu");
        articleService.listArticleByUserId(bo);
        return "/test/index";
    }

    @RequestMapping("/start")
    public String start(){
        return "/test/start";
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        // 设置模板文件中使用的参数
        model.addAttribute("title", "52Interview");
        return "/test/hello";
    }

    @RequestMapping("/startWithWebJar")
    public String startWithWebJar(){
        return "/test/startWithWebJar";
    }

    @PostMapping("/redis_test")
    public String redisTest(){

        try{
            String key="superblog:article:liked:userIds";
            String itemKey="1";
            Set<Long> userIds=new HashSet<>();
            userIds.add(1101L);
            userIds.add(1102L);
            userIds.add(1103L);
            userIds.add(1104L);
            userIds.add(1105L);

            redisUtils.hset(key,itemKey, JsonUtil.toJson(userIds));

            //1 点赞 文章点赞数+1
            like();

            //2 取消点赞
            unLike();

            //3 获取文章点赞数量
            Integer countArticleLike=getArticleTotalLikeCount();
            log.info("countArticleLike:"+countArticleLike);
        }catch (Exception ex)
        {
            log.error("操作失败! ex:"+ex.getMessage());
        }

        return  "";
    }

    private void  like()
    {
        String key="superblog:article:liked:userIds";
        String itemKey="1";
        String articleLikedResult = (String) redisUtils.hget(key,itemKey);
        Set<Long> likePostIdSet =articleLikedResult==null?new HashSet<>():JsonUtil.deserializeToSet(articleLikedResult,Long.class);
        likePostIdSet.add(1106L);
        redisUtils.hset(key,itemKey,JsonUtil.toJson(likePostIdSet));
    }

    private void  unLike()
    {
        String key="superblog:article:liked:userIds";
        String itemKey="1";
        String articleLikedResult = (String) redisUtils.hget(key,itemKey);
        Set<Long> likePostIdSet =articleLikedResult==null?new HashSet<>():JsonUtil.deserializeToSet(articleLikedResult,Long.class);
        likePostIdSet.remove(1106L);
        redisUtils.hset(key,itemKey,JsonUtil.toJson(likePostIdSet));
    }

    private Integer getArticleTotalLikeCount()
    {
        String key="superblog:article:liked:userIds";
        String itemKey="1";
        String articleLikedResult = (String) redisUtils.hget(key,itemKey);
        Set<Long> likePostIdSet =articleLikedResult==null?new HashSet<>():JsonUtil.deserializeToSet(articleLikedResult,Long.class);
        if (likePostIdSet == null) {
            return 0;
        }
        return likePostIdSet.size();
    }
}
