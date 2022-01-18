package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogdao.mapper.CarouselMapper;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DO.Carousel;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogpojo.VO.CarouselVO;
import com.zhike.blogservice.CarouselService;
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
 * CarouselServiceImpl at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<CarouselVO> getCarousels()
    {
        QueryWrapper<Carousel> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Carousel::getIsDeleted,false);

        List<Carousel> carousels=carouselMapper.selectList(queryWrapper);

        //封装VO
        List<CarouselVO> carouselVOS =
                carousels.stream()
                        .map(
                                item -> {
                                    CarouselVO vo = BeanHelper.convertBean(item, CarouselVO::new);
                                    return vo;
                                })
                        .collect(Collectors.toList());


        return carouselVOS;
    }
}
