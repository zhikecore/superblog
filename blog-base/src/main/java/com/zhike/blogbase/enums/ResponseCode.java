package com.zhike.blogbase.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

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
 * ResponseCode at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

public enum ResponseCode {
    /**
     * 成功返回的状态码
     */
    SUCCESS("0", "成功"),
    /**
     * 默认业务异常状态码
     */
    BUSINESS_DEFAULT_ERROR("-10000", "默认业务异常"),
    /**
     * 默认服务器异常状态码
     */
    SERVER_DEFAULT_ERROR("500", "服务器异常"),

    /**
     * 未授权
     */
    //UN_AUTHORIZED("401", "未授权"),
    UN_AUTHORIZED("401", "请登录!"),

    /**
     * 已点过赞
     */
    HAS_LIKED("1001", "您已经赞过!"),

    /**
     * 已收藏
     */
    HAS_FORKED("1002", "您已收藏过!"),
    /**
     * 收藏成功
     */
    FORKED_OK("1003", "收藏成功!"),
    /**
     * 违反数据库的完整性约束导致的异常
     */
    DATA_INTEGRITY_VIOLATION("1004", "违反数据库的完整性约束导致的异常!"),
    /**
     * 违反数据库的完整性约束导致的异常
     */
    DUPLICATE_KEY_ERROR("1004", "数据库插入重复Key!"),

    ;
    @EnumValue
    private final String code;

    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
