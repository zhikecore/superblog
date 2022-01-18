package com.zhike.blogpojo.VO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * ArticleTypeVO at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class ArticleTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer Id;

    public Integer ParenId;
    public String Name;
    public String Description;
    public LocalDateTime CreateTime;
    public LocalDateTime ModifyTime;

    public String Cover;

}
