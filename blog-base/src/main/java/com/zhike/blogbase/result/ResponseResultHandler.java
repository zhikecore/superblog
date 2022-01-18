package com.zhike.blogbase.result;

import com.zhike.blogbase.annotation.EnableResponseResult;
import com.zhike.blogbase.annotation.IgnoreResponseResult;
import com.zhike.blogbase.enums.ResponseCode;
import com.zhike.blogbase.utils.JsonUtil;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
 * ResponseResultHandler at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@ControllerAdvice(annotations = EnableResponseResult.class)
public class ResponseResultHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class converterType, ServerHttpRequest request, ServerHttpResponse response) {
        boolean ignoreResponseResult = methodParameter.getMethodAnnotation(IgnoreResponseResult.class) != null;
        if (ignoreResponseResult) {
            return body;
        }

        if (body instanceof ResponseResult) {
            return body;
        }

       if (body instanceof String) {
            return JsonUtil.toJson(ResponseResult.success(body));
       }
        return ResponseResult.success(body);
    }
}