package com.zhike.blogmanager.Article;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogdao.mapper.AdminuserMapper;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.VO.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 通用业务管理层
 * 与 DAO层交互,对多个DAO的组合
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
 * ArticleManager at 2022/1/16 21:17,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class ArticleManager {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AdminuserMapper adminuserMapper;

    public Adminuser findById(long id)
    {
        QueryWrapper<Adminuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Adminuser::getId, id);

        Adminuser adminuser =adminuserMapper.selectOne(queryWrapper);
        if (adminuser == null) {
            throw new BizException("用户不存在!");
        }

        return adminuser;
    }

    public IPage<ArticleVO> searchByPage(long start, long size, long articleTypeId)
    {
        Page<Article> page=new Page<>(start,size);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(articleTypeId>0,"ArticleTypeId",articleTypeId);

        IPage<Article> pagedResource=articleMapper.selectPage(page,queryWrapper);

        //参数一是当前页，参数二是每页个数
        //IPage<Article> page = new Page<>(1, 5);
        //IPage<Article> pagedResource=articleMapper.selectPage(page,null);

        //封装VO
        List<ArticleVO> items =
                pagedResource.getRecords().stream()
                        .map(
                                item -> {
                                    ArticleVO vo = BeanHelper.convertBean(item, ArticleVO::new);
                                    vo.setArticleTypeName("类型名");
                                    return vo;
                                })
                        .collect(Collectors.toList());

        IPage<ArticleVO> pagedVo=new Page<ArticleVO>();
        BeanUtils.copyProperties(pagedResource, pagedVo);
        pagedVo.setRecords(items);

        return pagedVo;
    }

    public IPage<ArticleVO> searchByPage(ArticleAO ao)
    {
        //参数一是当前页，参数二是每页个数
        Page<Article> page=new Page<>(ao.getPage(),ao.getLimit());

        IPage<Article> pagedResource=articleMapper.selectPage(page, Wrappers.<Article>lambdaQuery()
                .eq(ao.getArticleTypeId()>0,Article::getArticleTypeId,ao.getArticleTypeId())
                .like(ao.getTitle()!=null,Article::getTitle, ao.getTitle()));



        //封装VO
        List<ArticleVO> items =
                pagedResource.getRecords().stream()
                        .map(
                                item -> {
                                    ArticleVO vo = BeanHelper.convertBean(item, ArticleVO::new);
                                    vo.setArticleTypeName("类型名");
                                    return vo;
                                })
                        .collect(Collectors.toList());

        IPage<ArticleVO> pagedVo=new Page<ArticleVO>();
        BeanUtils.copyProperties(pagedResource, pagedVo);
        pagedVo.setRecords(items);

        return pagedVo;
    }
}
