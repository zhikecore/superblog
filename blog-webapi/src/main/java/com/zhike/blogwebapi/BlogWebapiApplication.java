package com.zhike.blogwebapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages="com.zhike")
@MapperScan("com.zhike.blogdao.mapper")
public class BlogWebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebapiApplication.class, args);
	}
}
