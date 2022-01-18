package com.zhike.blogbase.validation;

import com.zhike.blogbase.annotation.ParamCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
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
 * ParamConstraintValidated at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class ParamConstraintValidated implements ConstraintValidator<ParamCheck, Object> {

    /**
     * 合法的参数值,从注解中获取
     * */
    private  List<String> paramValues;

    @Override
    public void initialize(ParamCheck constraintAnnotation) {
        //初始化时获取注解上的值
        paramValues = Arrays.asList(constraintAnnotation.paramValues());
    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        //检查客户端参数是否在指定的参数列表中
        if (paramValues.contains(o)) {
            return true;
        }
        return false;
    }
}
