package com.zhike.blogadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.zhike.blogdao.mapper")
@SpringBootApplication(scanBasePackages = "com.zhike.*")
@ComponentScan(basePackages = "com.zhike")
public class BlogWebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogWebAdminApplication.class, args);
    }
}
