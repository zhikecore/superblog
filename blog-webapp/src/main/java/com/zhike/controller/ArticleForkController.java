package com.zhike.controller;

import com.zhike.blogbase.constant.RedisKeyConstant;
import com.zhike.blogbase.enums.ResponseCode;
import com.zhike.blogbase.result.ResponseResult;
import com.zhike.blogbase.utils.DateUtils;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.ServletUtil;
import com.zhike.blogdao.mapper.MyFavoritesMapper;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.MyFavorites;
import com.zhike.blogpojo.DTO.input.ArticleForkDto;
import com.zhike.blogpojo.DTO.input.ArticleLikeDto;
import com.zhike.blogservice.MyFavoriteService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
 * ArticleController at 2023/09/09 11:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Api(tags = "文章点赞")
@RestController
@RequestMapping("/articleFork")
@AllArgsConstructor
@Slf4j
public class ArticleForkController {

    @Resource private UserManager userManager;
    @Resource
    private MyFavoritesMapper myFavoritesMapper;
    @Resource
    private MyFavoriteService myFavoriteService;

    @PostMapping("/fork")
    @ResponseBody
    public ResponseResult fork(@Validated @RequestBody ArticleForkDto dto)
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

            //是否收藏过
            Boolean isForked=myFavoriteService.isForked(dto.getArticleId(),loginUser.getId());
            if(isForked)
            {
                result.setSuccess(false);
                result.setCode(ResponseCode.HAS_FORKED.getCode());
                result.setMsg(ResponseCode.HAS_FORKED.getMsg());
                result.setDesc(ResponseCode.HAS_FORKED.getMsg());
                return result;
            }

            //加入收藏
            Date now=new Date();
            MyFavorites myFavorite=new MyFavorites();
            myFavorite.setArticleId(dto.getArticleId());
            myFavorite.setUserId(loginUser.getId());
            myFavorite.setCreateTime(DateUtils.dateToLocalDateTime(now));
            myFavorite.setModifyTime(DateUtils.dateToLocalDateTime(now));
            myFavoritesMapper.insert(myFavorite);
        }catch (Exception ex)
        {
            log.error("收藏失败! error:"+ex.getMessage());
            result.setSuccess(false);
            result.setCode(ResponseCode.SERVER_DEFAULT_ERROR.getCode());
            result.setMsg(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            result.setDesc(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
            return  result;
        }

        result.setSuccess(true);
        result.setCode(ResponseCode.FORKED_OK.getCode());
        result.setMsg(ResponseCode.FORKED_OK.getMsg());
        result.setDesc(ResponseCode.FORKED_OK.getMsg());
        return result;
    }

}
