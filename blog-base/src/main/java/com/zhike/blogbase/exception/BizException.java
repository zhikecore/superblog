package com.zhike.blogbase.exception;

import com.zhike.blogbase.enums.ResponseCode;

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
 * BizException at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class BizException extends CustomException {
    public BizException(String message) {
        super(message, ResponseCode.BUSINESS_DEFAULT_ERROR.getCode());
    }

    public BizException(String message, String code) {
        super(message, code);
    }
}
