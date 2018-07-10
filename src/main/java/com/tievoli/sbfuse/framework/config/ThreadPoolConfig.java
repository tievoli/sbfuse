package com.tievoli.sbfuse.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 * 线程池配置类.
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 自定义线程池方法.
     *
     * @return
     */
    @Bean
    public Executor defaultThreadPool() {

        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();

        //设置线程池大小
        executor.setPoolSize(1);

        //设置优先级大小
        executor.setThreadPriority(100);

        //设置线程池前缀名称
        executor.setThreadNamePrefix("SbfuseThreadPool");
        //初始化
        executor.initialize();
        return executor;
    }

}
