package com.supermarket.store.management.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 全局线程池配置
 */
@Configuration
public class ExecutorConfig {
    //线程池大小
    @Value("3")
    private int corePoolSize;
    //线程池大小
    @Value("5")
    private int maxPoolSize;
    //缓存队列
    @Value("600000")
    private int poolQueueMaxSize;

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
