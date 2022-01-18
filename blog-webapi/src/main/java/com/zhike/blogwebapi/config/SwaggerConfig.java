package com.zhike.blogwebapi.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox-swagger2 配置
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
 * SwaggerConfig at 2021/2/12 17:27,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhike.blogwebapi.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SuperBlog 业务API文档")
                .description("基于Spring Boot的后端业务服务")
                .termsOfServiceUrl("http://localhost:9090/")
                .version("1.0")
                .contact(new Contact("智客工坊-顶级码农","https://www.52interview.com/","zhikecore@foxmail.com"))
                .build();
    }
}

//@Configuration
//@EnableSwagger2
//@EnableKnife4j
//public class SwaggerConfig {
//
//    @Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
//                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
//                        .termsOfServiceUrl("http://www.xx.com/")
//                        .contact("xx@qq.com")
//                        .version("1.0")
//                        .build())
//                //分组名称
//                .groupName("2.X版本")
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.github.xiaoymin.knife4j.controller"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }
//}