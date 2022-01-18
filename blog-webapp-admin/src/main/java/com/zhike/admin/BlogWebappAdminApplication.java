package com.zhike.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/4/11
 * Time: 10:34
 * Description: No Description
 */
@SpringBootApplication(scanBasePackages="com.zhike")
@MapperScan("com.zhike.blogdao.mapper")
public class BlogWebappAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogWebappAdminApplication.class, args);
    }

}
