package com.zhike.blogmanager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogdao.mapper.AdminuserMapper;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogdao.mapper.ArticleTypeMapper;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DO.ArticleType;
import com.zhike.blogpojo.VO.CacheArticleVO;
import org.apache.catalina.User;
import org.apache.commons.lang3.reflect.InheritanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
 * ArticleCacheManager at 2022/1/16 21:17,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Component
public class ArticleCacheManager {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private AdminuserMapper adminuserMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    public List<Article> getCacheArticles()
    {
        List<Article> cacheArticles=new ArrayList<Article>();

        try
        {
            String cacheKey="zhikecore:article";

            if(redisUtils.hasKey(cacheKey))
            {
                cacheArticles = redisUtils.lGet(cacheKey,0,-1,Article.class);
                //cacheArticles= JsonUtil.fromJson(stringRedisTemplate.opsForValue().get(cacheKey), CrmUserInfoDto.class);
            }

            if(cacheArticles!= null && cacheArticles.stream().count()>0)
                return  cacheArticles;

            //load from db
            System.out.println("begin load articles from db...");
            QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(Article::getIsUp, true)
                    .or()
                    .eq(Article::getIsHot,true);

            List<Article> topArticles=articleMapper.selectList(queryWrapper);

            if(topArticles!=null && topArticles.stream().count()>0)
                cacheArticles=topArticles;

            //set
            //redisUtils.lSet(cacheKey,cacheArticles,3600);
            //redisUtils.set(cacheKey, JsonUtil.toJson(cacheArticles),3600);
            // 缓存
            stringRedisTemplate.opsForValue().set(cacheKey, JsonUtil.toJson(cacheArticles));
            System.out.println("set articles in redis...");

        }catch (Exception ex)
        {
            throw  ex;
        }

        return  cacheArticles;
    }

    public  List<CacheArticleVO> getCacheArticlesV2()
    {
        List<CacheArticleVO> cacheArticles=new ArrayList<>();

        try
        {
            String cacheKey="zhikecore:article";

            if(redisUtils.hasKey(cacheKey))
            {
                //cacheArticles = redisUtils.lGet(cacheKey,0,-1,CacheArticleVO.class);
                //List<DeptDto> deptList =
                //          JsonUtil.fromJson(stringRedisTemplate.opsForValue().get(key), List.class);
                //cacheArticles=JsonUtil.fromJson(redisUtils.g,List.class);
                //Object reuslt= redisUtils.get(cacheKey);
                cacheArticles =
                          JsonUtil.fromJson(stringRedisTemplate.opsForValue().get(cacheKey), List.class);
            }

            if(cacheArticles!= null && cacheArticles.stream().count()>0)
                return  cacheArticles;

            //load from db
            System.out.println("begin load articles from db...");
            QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(Article::getIsUp, true)
                    .or()
                    .eq(Article::getIsHot,true);

            //achieve data from database
            List<Article> topArticles=articleMapper.selectList(queryWrapper);

            if(topArticles!=null && topArticles.stream().count()>0)
            {
               cacheArticles= BeanHelper.convertBeans(topArticles,CacheArticleVO::new);
            }

            List<Integer> userIds=topArticles.stream().map(c->c.getUserId()).collect(Collectors.toList());
            List<Adminuser> authors=new ArrayList<Adminuser>();
            List<Integer> articleTypeIds=topArticles.stream().map(c->c.getArticleTypeId()).collect(Collectors.toList());
            List<ArticleType> articleTypes=new ArrayList<ArticleType>();

            if(cacheArticles!=null && cacheArticles.stream().count()>0)
            {
                QueryWrapper<Adminuser> userQueryWrapper=new QueryWrapper<>();
                userQueryWrapper.lambda()
                        .in(Adminuser::getId,userIds)
                        .select(Adminuser::getId,
                                Adminuser::getAccount,
                                Adminuser::getAvatar);
               authors=adminuserMapper.selectList(userQueryWrapper);

                QueryWrapper<ArticleType> articleTypeQueryWrapper=new QueryWrapper<>();
                articleTypeQueryWrapper.lambda()
                        .in(ArticleType::getId,articleTypeIds)
                        .select(ArticleType::getId,
                                ArticleType::getName);;

                articleTypes=articleTypeMapper.selectList(articleTypeQueryWrapper);

                for (CacheArticleVO item:cacheArticles) {
                    Optional<ArticleType> articleType=
                            articleTypes.stream()
                                    .filter(c->c.getId().equals(item.getArticleTypeId()))
                                    .findFirst();

                    Optional<Adminuser> author=
                            authors.stream()
                                    .filter(c->c.getId().equals(item.getUserId()))
                                    .findFirst();

                    if(articleType.isPresent())
                        item.setArticleType(articleType.get());

                    if(author.isPresent())
                        item.setAuthor(author.get());

                }
            }

            //set
            String jsonResult = JsonUtil.toJson(cacheArticles);
            stringRedisTemplate.opsForValue().set(cacheKey, jsonResult);
            System.out.println("set articles in redis...");

        }catch (Exception ex)
        {
            throw  ex;
        }

        return cacheArticles;
    }

}
