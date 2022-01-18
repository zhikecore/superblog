package com.zhike.blogpojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
 * Carousel at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Carousel implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 标题
     */
    @TableField("Name")
    private String Name;

    /**
     * 跳转链接
     */
    @TableField("Link")
    private String Link;

    /**
     * 封面
     */
    @TableField("Cover")
    private String Cover;

    /**
     * 描述
     */
    @TableField("Description")
    private String Description;

    @TableField("IsDeleted")
    private Boolean IsDeleted;

    @TableField("IsActive")
    private Boolean IsActive;

    /**
     * CarouselItem 样式
     * */
    @TableField("carousel_item_class")
    private  String CarouselItemCls;

    @TableField("CreateTime")
    private LocalDateTime CreateTime;

    @TableField("ModifyTime")
    private LocalDateTime ModifyTime;
}
