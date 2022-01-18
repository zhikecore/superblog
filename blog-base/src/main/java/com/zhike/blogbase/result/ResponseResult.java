package com.zhike.blogbase.result;

import com.zhike.blogbase.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

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
 * ResponseResult at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class ResponseResult<T> implements Serializable {

    private String code;

    private Boolean success;

    private String msg;

    public String desc;

    private T data;

//    public static ResponseResult<Boolean> success() {
//        ResponseResult<Boolean> responseResult = new ResponseResult<>();
//        responseResult.setCode(ResponseCode.SUCCESS.getCode());
//        responseResult.setSuccess(true);
//        responseResult.setMsg(ResponseCode.SUCCESS.getMsg());
//        responseResult.setData(true);
//        return responseResult;
//    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMsg(ResponseCode.SUCCESS.getMsg());
        result.setSuccess(true);
        result.setData(data);
        return result;
    }



}
