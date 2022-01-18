package com.zhike.blogservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhike.blogbase.result.PagedResult;
import com.zhike.blogpojo.AO.ArticleTypeAO;
import com.zhike.blogpojo.DO.ArticleType;
import com.zhike.blogpojo.VO.ArticleTypeVO;
import com.zhike.blogpojo.VO.ArticleVO;

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
 * ArticleTypeService at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public interface ArticleTypeService extends IService<ArticleType> {

    IPage<ArticleTypeVO> searchByPage(ArticleTypeAO ao);

    IPage<ArticleVO> getArticlesByTypeId(long start, long size, long articleTypeId);
}
