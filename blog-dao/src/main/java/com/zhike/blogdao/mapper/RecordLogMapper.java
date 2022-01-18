package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.Product;
import com.zhike.blogpojo.DO.OperationRecordLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/4/4
 * Time: 10:36
 * Description: No Description
 */
@Mapper
@Repository
@Component(value = "RecordLogMapper")
public interface RecordLogMapper extends BaseMapper<OperationRecordLog> {

}