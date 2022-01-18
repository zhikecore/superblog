package com.zhike.blogbase.exception;

import com.zhike.blogbase.enums.ResponseCode;
import com.zhike.blogbase.result.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
 * GlobalExceptionHandler at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();

        ResponseResult<Object> result = new ResponseResult<>();
        result.setSuccess(false);

        if (exception instanceof CustomException) {
            CustomException ex = (CustomException) exception;
            result.setCode(ex.getCode());
            result.setMsg(ex.getMessage());
            return result;
        }

        if (exception instanceof MethodArgumentNotValidException) {
            // 解决参数校验的异常
            MethodArgumentNotValidException error = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = error.getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessages =
                    fieldErrors.stream()
                            .map(x -> x.getField() + x.getDefaultMessage())
                            .sorted()
                            .collect(Collectors.joining("; "));
            result.setMsg(errorMessages);
            result.setCode(ResponseCode.BUSINESS_DEFAULT_ERROR.getCode());
            return result;
        }

        if (exception instanceof ConstraintViolationException) {
            // request param 参数校验的异常
            ConstraintViolationException error = (ConstraintViolationException) exception;
            String errorMessages =
                    error.getConstraintViolations().stream()
                            .map(
                                    violation ->
                                            String.format(
                                                    "%s %s",
                                                    StreamSupport.stream(
                                                            violation
                                                                    .getPropertyPath()
                                                                    .spliterator(),
                                                            false)
                                                            .reduce((first, second) -> second)
                                                            .orElse(null),
                                                    violation.getMessage()))
                            .collect(Collectors.joining(";"));
            result.setMsg(errorMessages);
            result.setCode(ResponseCode.BUSINESS_DEFAULT_ERROR.getCode());
            return result;
        }

        result.setCode(ResponseCode.SERVER_DEFAULT_ERROR.getCode());
        result.setMsg(ResponseCode.SERVER_DEFAULT_ERROR.getMsg());
        result.setDesc(exception.toString());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
