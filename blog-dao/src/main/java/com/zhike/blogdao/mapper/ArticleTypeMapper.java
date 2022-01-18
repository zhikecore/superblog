package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.ArticleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/2/12
 * Time: 17:38
 * Description: No Description
 */
@Mapper
@Repository
@Component(value = "ArticleTypeMapper")
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {


}
