package com.zhike.blogpojo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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
 * DataTableVO at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class DataTableVO {
    @JsonProperty("sEcho")
    private  String sEcho;

    @JsonProperty("iTotalRecords")
    private  long iTotalRecords;

    @JsonProperty("iTotalDisplayRecords")
    private  long iTotalDisplayRecords;

    @JsonProperty("aaData")
    private List<UserVO> aaData;
}
