package com.zhike.blogpojo.VO;

import com.zhike.blogbase.annotation.ParamCheck;
import lombok.Data;

import javax.accessibility.AccessibleStreamable;
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
 * UserVO at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class UserVO  implements Serializable {

//    private  long id;
//    private  String username;
//    private  String account;
//    private  String passwordSalt;
//    private  String phoneValideCode;
//    private  String phone;
//    private  String qq;
//    private  String email;
//    private  String nickName;
//    private  String realName;
//    private  String avatar;
//    private  Boolean isUse;
//    private  String description;
//    private  LocalDateTime CreateTime;
//    private  LocalDateTime modifyTime;

    private static final long serialVersionUID = 1L;

    private Integer Id;

    /**
     * 账户
     */
    private String Account;

    /**
     * 密码
     */
    private String PasswordSalt;

    /**
     * 验证码
     */
    private String PhoneValideCode;

    /**
     * 移动号码
     */
    private String Phone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱
     */
    private String Email;

    /**
     * 昵称
     */
    private String NickName;

    /**
     * 真名
     */
    private String RealName;

    /**
     * 头像
     */
    private String Avatar;

    /**
     * 注册IP
     */
    private String RegIp;

    /**
     * 是否冻结 '是否冻结，0为不冻结，1为冻结'
     */
    private Boolean IsUse;

    /**
     * 描述
     */
    private String Description;

    /**
     * 创建时间
     */
    private LocalDateTime CreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime ModifyTime;
}
