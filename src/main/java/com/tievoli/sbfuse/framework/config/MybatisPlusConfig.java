package com.tievoli.sbfuse.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.tievoli.sbfuse.biz.**.*Mapper")
public class MybatisPlusConfig {



}
