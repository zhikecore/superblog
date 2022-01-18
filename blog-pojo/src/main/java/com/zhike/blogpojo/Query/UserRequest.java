package com.zhike.blogpojo.Query;

import com.zhike.blogbase.annotation.ParamCheck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * UserRequest at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    /**
     * 用户名
     */
    private String accoutname;

    /**
     * 性别
     */
    @ParamCheck(paramValues = {"男", "女"})
    private String sex;
}
