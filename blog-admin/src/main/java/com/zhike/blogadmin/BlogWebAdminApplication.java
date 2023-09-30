package com.zhike.blogadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.zhike")
@MapperScan("com.zhike.blogdao.mapper")
public class BlogWebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogWebAdminApplication.class, args);
    }
}
