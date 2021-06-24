package com.supermarket.store.management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * spring boot项目的启动类，监听tomcat的启动，启动spring boot流程
 */
// 定义spring ioc包扫描范围
@ComponentScan(basePackages = {"com"})
// 定义spring-data-jpa的包范围
@EnableJpaRepositories(
        basePackages = {"com.supermarket.store.management.api.dao"})
// 注明是spring boot的启动类
@SpringBootApplication
public class SupermarketStoreManagementApiApplication {

    public static void main(String[] args) {
        // 启动spring boot流程
        SpringApplication.run(SupermarketStoreManagementApiApplication.class, args);
    }

}
