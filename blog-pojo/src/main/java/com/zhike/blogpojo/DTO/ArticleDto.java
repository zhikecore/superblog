package com.zhike.blogpojo.DTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
 * ArticleDto at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class ArticleDto extends PagedQueryDto {

//    @ApiModelProperty("文章Id")
//    private Integer Id;

    @ApiModelProperty("文章类型Id")
    private Integer ArticleTypeId;

    @ApiModelProperty("标题")
    private String Title;

}
