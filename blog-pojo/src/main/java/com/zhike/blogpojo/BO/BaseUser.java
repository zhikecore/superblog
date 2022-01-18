package com.zhike.blogpojo.BO;

import lombok.Data;

import java.util.Date;
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
 * BaseUser at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class BaseUser {

    /**
     * 用户编号(原GUID)
     */
    private String id;

    /**
     * 用户编号(表主键Id),-1 表示不存在该用户编号
     */
    private long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private int sex;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户类型
     */
    private int userType;

    /**
     * 创建时间(注册时间)
     */
    private Date createTime;

    /**
     * 用户缓存Key
     */
    private String userCacheKey;

    /**
     * 老业务系统所需的User Token
     */
    private String OldUserToken;

    /**
     * 手机号（脱敏）
     */
    private String mobile;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 是否是新用户
     */
    private boolean isNewUser;

    /**
     * 是否是管理员
     */
    private boolean isManager;

    /**
     * app key列表
     */
    private List<String> appArray;

    /**
     * 状态
     */
    private int status;

    /**
     * 第三方平台账号绑定的集合
     */
    private List<Integer> thirdPlatforms;


    /**
     * 内部部门id
     */
    private int deptId;
}
