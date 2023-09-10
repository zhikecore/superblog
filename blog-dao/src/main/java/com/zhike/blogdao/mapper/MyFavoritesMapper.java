package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogbase.result.SuperPage;
import com.zhike.blogpojo.BO.ArticleByUserIdBo;
import com.zhike.blogpojo.DO.MyFavorites;
import com.zhike.blogpojo.DO.Product;
import com.zhike.blogpojo.VO.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component(value = "MyFavoritesMapper")
public interface MyFavoritesMapper extends BaseMapper<MyFavorites> {

    SuperPage<ArticleVO> listMyForkedArticle(
            @Param("page") IPage<ArticleVO> page,
            @Param("queryParams") ArticleByUserIdBo bo);

}
