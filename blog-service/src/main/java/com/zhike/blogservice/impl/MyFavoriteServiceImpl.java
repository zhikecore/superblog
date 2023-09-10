package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogbase.annotation.ArticleParamsConvert;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.result.SuperPage;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogdao.mapper.MyFavoritesMapper;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DO.MyFavorites;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import com.zhike.blogservice.MyFavoriteService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class MyFavoriteServiceImpl extends ServiceImpl<MyFavoritesMapper, MyFavorites> implements MyFavoriteService {

    @Autowired
    private MyFavoritesMapper myFavoritesMapper;

    @Override
    public Boolean isForked(Integer articleId,Integer userId)
    {
        QueryWrapper<MyFavorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MyFavorites::getArticleId, articleId);
        queryWrapper.lambda().eq(MyFavorites::getUserId, userId);

        MyFavorites myFavorite =myFavoritesMapper.selectOne(queryWrapper);
        return  myFavorite==null?false:true;
    }


    @Override
    public SuperPage<ArticleVO> listArticles(ArticleByUserIdBo bo)
    {
        SuperPage<ArticleVO> page = new SuperPage<>();
        //page.setSearchCount(false);
        SuperPage<ArticleVO> articles=myFavoritesMapper.listMyForkedArticle(page,bo);
        return  articles;
    }
}
