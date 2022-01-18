package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogdao.mapper.ArticleTypeMapper;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogpojo.AO.ArticleTypeAO;
import com.zhike.blogpojo.DO.ArticleType;
import com.zhike.blogpojo.VO.ArticleTypeVO;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
 * ArticleTypeServiceImpl at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper,ArticleType> implements ArticleTypeService {

    @Autowired
    private  ArticleTypeMapper articleTypeMapper;

    @Autowired
    private ArticleManager articleManager;

    @Override
    public IPage<ArticleTypeVO> searchByPage(ArticleTypeAO ao){

        //拼接查询条件
        LambdaQueryWrapper<ArticleType> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select().like(StringUtil.isNotEmpty(ao.getName()),ArticleType::getName,ao.getName());

        Page<ArticleType> page = new Page<>(ao.getPage(),ao.getLimit(), true);

        //查询
        IPage<ArticleType> pagedResource=articleTypeMapper.selectPage(page,queryWrapper);

        //封装VO
        List<ArticleTypeVO> items =
                pagedResource.getRecords().stream()
                        .map(
                                item -> {
                                    ArticleTypeVO vo = BeanHelper.convertBean(item, ArticleTypeVO::new);
                                    vo.setCover("https://img.zhikestreet.com/202011170805.png");
                                    return vo;
                                })
                        .collect(Collectors.toList());

        IPage<ArticleTypeVO> pagedVo=new Page<ArticleTypeVO>();
        BeanUtils.copyProperties(pagedResource, pagedVo);
        pagedVo.setRecords(items);

        return pagedVo;
    }

    @Override
    public  IPage<ArticleVO> getArticlesByTypeId(long start, long size, long articleTypeId)
    {
        IPage<ArticleVO> pagedVo=articleManager.searchByPage(start,size,articleTypeId);
        return pagedVo;
    }
}
