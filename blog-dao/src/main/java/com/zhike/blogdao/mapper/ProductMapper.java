package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/2/17
 * Time: 11:33
 * Description: No Description
 */
@Mapper
@Repository
@Component(value = "ProductMapper")
public interface ProductMapper extends BaseMapper<Product> {

}
