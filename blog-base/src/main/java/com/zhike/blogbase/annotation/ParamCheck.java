package com.zhike.blogbase.annotation;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/6/20
 * Time: 8:55
 * Description: No Description
 */

import com.zhike.blogbase.validation.ParamConstraintValidated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
 * ParamCheck at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Target({ ElementType.FIELD}) //只允许用在类的字段上
@Retention(RetentionPolicy.RUNTIME) //注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
@Constraint(validatedBy = ParamConstraintValidated.class)
public @interface ParamCheck {
    /**
     * 合法的参数值
     * */
    String[] paramValues();

    /**
     * 提示信息
     * */
    String message() default "参数不为指定值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}