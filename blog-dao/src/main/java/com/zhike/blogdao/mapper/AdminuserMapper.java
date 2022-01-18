package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/1/3
 * Time: 15:03
 * Description: No Description
 */

@Mapper
@Repository
@Component(value = "AdminuserMapper")
public interface AdminuserMapper extends BaseMapper<Adminuser> {

}
