package com.zhike;

//import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages="com.zhike")
@ServletComponentScan(basePackages = "com.zhike")
public class BlogWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebappApplication.class, args);
	}

}
