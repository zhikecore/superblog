package com.zhike.blogpojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Copyright (C) 2023  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * Product at 2023/09/09 11:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
@TableName("my_favorites")
public class MyFavorites {
    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @TableField("UserId")
    private Integer userId;

    @TableField("ArticleId")
    private Integer articleId;

    @TableField("IsDeleted")
    private Boolean isDeleted;

    @TableField("CreateTime")
    private LocalDateTime createTime;

    @TableField("ModifyTime")
    private LocalDateTime modifyTime;
}
