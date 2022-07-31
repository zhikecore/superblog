package com.zhike.blogpojo.BO;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
 * CreateAdminUserRequestBo at 2022/7/10 15:30,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class CreateAdminUserRequestBo {
    private String phone;
    private String account;
    private String nickname;
    private String password;
    private String rePassword;
}
