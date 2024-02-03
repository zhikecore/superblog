package com.zhike.blogservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhike.blogbase.annotation.ArticleParamsConvert;
import com.zhike.blogbase.result.PagedResult;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.BO.ArticleCreateBo;
import com.zhike.blogpojo.BO.ArticleUpdateBo;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DTO.input.ArticleCreateDto;
import com.zhike.blogpojo.VO.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
 * ArticleService at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

public interface ArticleService extends IService<Article> {

   String test();
   ArticleVO findById(long id);
   PagedResult<ArticleVO> findList(ArticleAO articleAo);
   //IPage<Article> searchByPage(long start, long size, String name);//name
   //public ArticleVO getPageData(String keyword, long pageIndex, long pageSize);
   List<Article> getCarousels();

   int insertArticle(String tag,String Title);

   int updateByid(int id ,String tags,String title);
   IPage<ArticleVO> searchByPage(long start, long size, String name);

   @ArticleParamsConvert()
   List<ArticleVO> listArticleByUserId(ArticleByUserIdBo bo);
   void create(ArticleCreateBo bo);

   void modify(ArticleUpdateBo bo);
}
