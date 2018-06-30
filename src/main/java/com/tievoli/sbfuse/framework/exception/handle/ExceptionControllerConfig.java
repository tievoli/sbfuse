package com.tievoli.sbfuse.framework.exception.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * 异常控制器配置类.
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class ExceptionControllerConfig {

    /**
     * 错误试图解析器.
     */
    @Autowired(required = false)
    private List<ErrorViewResolver> errorViewResolvers;

    /**
     * 服务器属性.
     */
    private final ServerProperties serverProperties;

    /**
     * 初始化服务器属性.
     *
     * @param serverProperties 服务器属性对象.
     */
    public ExceptionControllerConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    /**
     * 注入异常处理控制器.
     *
     * @param errorAttributes 错误属性
     * @return 返回错误控制类
     */
    @Bean
    public ExceptionController exceptionController(ErrorAttributes errorAttributes) {
        return new ExceptionController(errorAttributes, this.errorViewResolvers, this.serverProperties.getError());
    }
}
