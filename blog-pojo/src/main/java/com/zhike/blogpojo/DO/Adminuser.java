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
 * Adminuser at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Adminuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 账户
     */
    @TableField("Account")
    private String Account;

    /**
     * 密码
     */
    @TableField("PasswordSalt")
    private String PasswordSalt;

    /**
     * 验证码
     */
    @TableField("PhoneValideCode")
    private String PhoneValideCode;

    /**
     * 移动号码
     */
    @TableField("Phone")
    private String Phone;

    /**
     * QQ
     */
    @TableField("QQ")
    private String qq;

    /**
     * 邮箱
     */
    @TableField("Email")
    private String Email;

    /**
     * 昵称
     */
    @TableField("NickName")
    private String NickName;

    /**
     * 真名
     */
    @TableField("RealName")
    private String RealName;

    /**
     * 头像
     */
    @TableField("Avatar")
    private String Avatar;

    /**
     * 注册IP
     */
    @TableField("RegIp")
    private String RegIp;

    /**
     * 是否冻结 '是否冻结，0为不冻结，1为冻结'
     */
    @TableField("IsUse")
    private Boolean IsUse;

    /**
     * 描述
     */
    @TableField("Description")
    private String Description;



    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private LocalDateTime CreateTime;

    /**
     * 修改时间
     */
    @TableField("ModifyTime")
    private LocalDateTime ModifyTime;


}