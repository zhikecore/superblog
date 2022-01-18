package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.Carousel;
import com.zhike.blogpojo.DO.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component(value = "CarouselMapper")
public interface CarouselMapper extends BaseMapper<Carousel> {

}