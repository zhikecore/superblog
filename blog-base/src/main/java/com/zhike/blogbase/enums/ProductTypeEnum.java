package com.zhike.blogbase.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
 * ProductTypeEnum at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Getter
@AllArgsConstructor
public enum ProductTypeEnum implements IEnum<Integer> {

    // 博文
    Article(0, "博文"),

    // 解决方案
    Solution(1, "解决方案"),

    // 周刊
    Weekly(2, "周刊")

    ;

    @JsonValue
    private final Integer status;
    private final String desc;


    @Override
    public Integer getValue() {
        return status;
    }

}
