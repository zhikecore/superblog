package com.zhike.blogbase.exception;

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
 * RuntimeException at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class
CustomException extends RuntimeException implements Serializable {
    private String message;
    private String code;

    public CustomException(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
