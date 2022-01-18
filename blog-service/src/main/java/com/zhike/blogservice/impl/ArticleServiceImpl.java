package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.result.PagedResult;
import com.zhike.blogbase.result.SuperPage;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
 * ArticleServiceImpl at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public String test() {
        return "hello";
    }

    @Override
    public ArticleVO findById(long id)
    {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Article::getId, id);

        Article article =articleMapper.selectOne(queryWrapper);
        if (article == null) {
            throw new BizException("文章不存在");
        }

        ArticleVO articleVO = BeanHelper.convertBean(article, ArticleVO::new);
        return  articleVO;
    }

    /**
     *
     * @param ao
     * @return
     */
    @Override
    public  PagedResult<ArticleVO> findList(ArticleAO ao) {

        //拼接查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select()
                .eq(ao.getArticleTypeId()!=null, Article::getArticleTypeId, ao.getArticleTypeId())
                .like(StringUtil.isNotEmpty(ao.getTitle()),Article::getTitle,ao.getTitle());
        //Integer articleTypeId=33;
        //queryWrapper.select().eq(articleTypeId!=null, Article::getArticleTypeId, articleTypeId);

        Page<Article> page = new Page<>(ao.getPage(),ao.getLimit(), true);
        //page.setOrders(Collections.singletonList(OrderItem.desc("CreateTime")));

        //查询
        //articleMapper.selectPage(page, queryWrapper);8
        Page<Article> pagedResource = articleMapper.selectPage(page, queryWrapper);


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

        PagedResult<ArticleVO> pagedResult = new PagedResult<>();
        pagedResult.setPage(pagedResource.getCurrent());
        pagedResult.setLimit(pagedResource.getSize());
        pagedResult.setItems(items);
        pagedResult.setTotal(pagedResource.getTotal());
        pagedResult.setTotalPages(pagedResource.getPages());
        pagedResult.setHasMore(pagedResource.hasNext());


        return pagedResult;
        /*Page<Article> page=new Page<>(ao.getPage(),ao.getLimit(), true);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(ao.getTitle()))
        {
            //queryWrapper.like("title", "%"+ao.getTitle()+"%");
            queryWrapper.lambda().like(ao.getTitle()!=null,Article::getTitle,ao.getTitle());
        }

        //Integer articleTypeId=33;
        //queryWrapper.lambda().eq(articleTypeId!=null,Article::getArticleTypeId,articleTypeId);

        articleMapper.selectPage(page,queryWrapper);


        return null;*/
    }

    @Override
    public IPage<ArticleVO> searchByPage(long start, long size, String name)
    {
        Page<Article> page=new Page<>(start,size);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(name))
            queryWrapper.like("title", "%"+name+"%");

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

//    @Override
//    public IPage<Article> searchByPage(long start, long size, String name)
//    {
//        Page<Article> page=new Page<>(start,size);
//        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
//        if(StringUtils.isNotBlank(name))
//            queryWrapper.like("title", "%"+name+"%");
//        articleMapper.selectPage(page,queryWrapper);
//        return page;
//    }

//    @Override
//    public ArticleVO getPageData(String keyword, long pageIndex, long pageSize)
//    {
//        ArticleVO vo=new ArticleVO();
//        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
//        if(StringUtils.isNotEmpty(keyword))
//            queryWrapper.lambda().like(Article::getTitle,keyword);
//
//        Page<Article> page = new Page<>(pageIndex,pageSize);
//        IPage<Article> articlePage = articleMapper.selectPage(page,queryWrapper);
//        System.out.println("总条数"+articlePage.getTotal());
//        System.out.println("总页数"+articlePage.getPages());
//
//        vo.setPageIndex(pageIndex);
//        vo.setPageSize(pageSize);
//        vo.setTotal(articlePage.getTotal());
//        vo.setUserList(articlePage.getRecords());
//
//        return vo;
//    }

    @Override
    public List<Article> getCarousels()
    {
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Article::getIsUp, true)
                .or()
                .eq(Article::getIsHot,true);

        List<Article> topArticles=articleMapper.selectList(queryWrapper);
        return topArticles;
    }

    @Override
    public  int insertArticle(String tags, String title)
    {
        return articleMapper.insertArticle(tags,title);
    }

    @Override
    public int updateByid(int id ,String tags,String title)
    {
        return  articleMapper.updateByid(id,tags,title);
    }
}
