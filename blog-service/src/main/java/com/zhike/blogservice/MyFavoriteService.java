package com.zhike.blogservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhike.blogbase.result.SuperPage;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.DO.MyFavorites;
import com.zhike.blogpojo.VO.ArticleVO;
import jdk.nashorn.internal.ir.IdentNode;

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
public interface MyFavoriteService extends IService<MyFavorites> {
    Boolean isForked(Integer articleId,Integer userId);
    SuperPage<ArticleVO> listArticles(ArticleByUserIdBo bo);
}
