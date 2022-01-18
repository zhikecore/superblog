package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogdao.mapper.RecordLogMapper;
import com.zhike.blogpojo.DO.OperationRecordLog;
import com.zhike.blogservice.RecordLogService;
import org.springframework.stereotype.Service;

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
 * RecordLogServiceImpl at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class RecordLogServiceImpl extends ServiceImpl<RecordLogMapper,OperationRecordLog> implements RecordLogService  {


}

