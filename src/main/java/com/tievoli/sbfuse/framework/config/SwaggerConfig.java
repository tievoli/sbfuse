package com.tievoli.sbfuse.framework.config;

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
 * Swagger JavaConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String version = "1.0";

    //创建Rest API.
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //设置API信息
                .apiInfo(apiInfo())
                //选择构建器
                .select()
                //扫描API
                .apis(RequestHandlerSelectors.basePackage("com.tievoli.sbfuse"))
                .paths(PathSelectors.any())
                .build();
    }

    //API详细信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Sbfuse API")
                //联系人
                .contact(new Contact("sbfuse", "www.sbfuse.com", "admin@admin.com"))
                //API版本
                .version(version)
                //API描述
                .description("API 描述")
                .build();
    }

}
