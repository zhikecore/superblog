package com.zhike.controller;

import com.zhike.blogbase.constant.RedisKeyConstant;
import com.zhike.blogbase.enums.ResponseCode;
import com.zhike.blogbase.result.ResponseResult;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogbase.utils.ServletUtil;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DTO.input.ArticleLikeDto;
import com.zhike.blogpojo.DTO.input.ArticleUnLikeDto;
import com.zhike.blogpojo.VO.GetArticleLikeNumVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

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
 * ArticleController at 2023/07/06 11:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Api(tags = "文章点赞")
@RestController
@RequestMapping("/articleLike")
@AllArgsConstructor
@Slf4j
public class ArticleLikeController {

    @Resource
    private ArticleManager articleManager;

    @Resource private UserManager userManager;

    @Resource
    private RedisUtils redisUtils;

    @PostMapping("/like")
    @ResponseBody
    public ResponseResult like(@Validated @RequestBody ArticleLikeDto dto)
    {
        ResponseResult result=new ResponseResult();
        try
        {
            HttpSession session =  ServletUtil.getRequest().getSession();
            String account = (String) session.getAttribute("aname");
            Adminuser loginUser=userManager.findByAccount(account);
            if(loginUser==null)
            {
                result.setSuccess(false);
                result.setCode(ResponseCode.UN_AUTHORIZED.getCode());
                result.setMsg(ResponseCode.UN_AUTHORIZED.getMsg());
                result.setDesc(ResponseCode.UN_AUTHORIZED.getMsg());
                return result;
            }

            String key= RedisKeyConstant.ARTICLE_LIKED_USERS;
            String itemKey=dto.getArticleId().toString();
            String articleLikedResult = (String) redisUtils.hget(key,itemKey);
            Set<Integer> likePostIdSet =articleLikedResult==null?new HashSet<>(): JsonUtil.deserializeToSet(articleLikedResult,Integer.class);
            if(likePostIdSet.contains(loginUser.getId()))
            {
                result.setSuccess(false);
                result.setCode(ResponseCode.HAS_LIKED.getCode());
                result.setMsg(ResponseCode.HAS_LIKED.getMsg());
                result.setDesc(ResponseCode.HAS_LIKED.getMsg());
                return result;
            }
            likePostIdSet.add(loginUser.getId());
            redisUtils.hset(key,itemKey,JsonUtil.toJson(likePostIdSet));

        }catch (Exception ex)
        {
            log.error("点赞失败! error:"+ex.getMessage());
            result.setSuccess(false);
            result.setCode(ResponseCode.SERVER_DEFAULT_ERROR.getCode());
            result.setMsg(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            result.setDesc(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            return  result;
        }

        result.setSuccess(true);
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMsg(ResponseCode.SUCCESS.getMsg());
        result.setDesc(ResponseCode.SUCCESS.getMsg());
        return result;
    }

    @PostMapping("/unlike")
    @ResponseBody
    public ResponseResult  unLike(@Validated @RequestBody ArticleUnLikeDto dto)
    {
        ResponseResult result=new ResponseResult();
        try
        {
            HttpSession session =  ServletUtil.getRequest().getSession();
            String account = (String) session.getAttribute("aname");
            Adminuser loginUser=userManager.findByAccount(account);

            String key=RedisKeyConstant.ARTICLE_LIKED_USERS;
            String itemKey=dto.getArticleId().toString();
            String articleLikedResult = (String) redisUtils.hget(key,itemKey);
            Set<Integer> likePostIdSet =articleLikedResult==null?new HashSet<>(): JsonUtil.deserializeToSet(articleLikedResult,Integer.class);
            likePostIdSet.remove(loginUser.getId());
            redisUtils.hset(key,itemKey,JsonUtil.toJson(likePostIdSet));

        }catch (Exception ex)
        {
            log.error("点赞失败! error:"+ex.getMessage());
            result.setSuccess(false);
            result.setCode(ResponseCode.SERVER_DEFAULT_ERROR.getCode());
            result.setMsg(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            result.setDesc(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            return  result;
        }

        result.setSuccess(true);
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMsg(ResponseCode.SUCCESS.getMsg());
        result.setDesc(ResponseCode.SUCCESS.getMsg());
        return result;
    }

    @RequestMapping(value = "/likeNum/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<GetArticleLikeNumVo> getLikeNum(@PathVariable Integer id)
    {
        ResponseResult result=new ResponseResult();
        try
        {
            HttpSession session =  ServletUtil.getRequest().getSession();
            String account = (String) session.getAttribute("aname");
            Adminuser loginUser=userManager.findByAccount(account);
            if(loginUser==null)
            {
                result.setSuccess(false);
                result.setCode(ResponseCode.UN_AUTHORIZED.getCode());
                result.setMsg(ResponseCode.UN_AUTHORIZED.getMsg());
                result.setDesc(ResponseCode.UN_AUTHORIZED.getMsg());
                return result;
            }

            String key= RedisKeyConstant.ARTICLE_LIKED_USERS;
            String itemKey=id.toString();
            String articleLikedResult = (String) redisUtils.hget(key,itemKey);
            Set<Integer> likePostIdSet =articleLikedResult==null?new HashSet<>(): JsonUtil.deserializeToSet(articleLikedResult,Integer.class);
            Integer likedNum=0;
            if (likePostIdSet != null) {
               likedNum=likePostIdSet.size();
            }
            result.setSuccess(true);
            result.setCode(ResponseCode.SUCCESS.getCode());
            result.setMsg(ResponseCode.SUCCESS.getMsg());
            result.setDesc(ResponseCode.SUCCESS.getMsg());
            GetArticleLikeNumVo getArticleLikeNumVo=new GetArticleLikeNumVo();
            getArticleLikeNumVo.setLikeNum(likedNum);
            result.setData(getArticleLikeNumVo);
        }catch (Exception ex)
        {
            log.error("点赞失败! error:"+ex.getMessage());
            result.setSuccess(false);
            result.setCode(ResponseCode.SERVER_DEFAULT_ERROR.getCode());
            result.setMsg(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            result.setDesc(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
        }

        return result;
    }
}
