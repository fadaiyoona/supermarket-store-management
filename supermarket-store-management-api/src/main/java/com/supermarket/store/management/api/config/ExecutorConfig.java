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
 * 配置一个线程池的操作类，并注册成IOC组件
 * 用于执行所有的异步任务
 */
@Configuration
public class ExecutorConfig {
    /**
     * 核心线程数
     */
    @Value("3")
    private int corePoolSize;
    /**
     * 最大线程数
     */
    @Value("5")
    private int maxPoolSize;
    /**
     * 缓存队列大小
     */
    @Value("600000")
    private int poolQueueMaxSize;

    /**
     * 注册一个线程池操作类，成IOC组件
     * @return 线程池操作类
     */
    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
