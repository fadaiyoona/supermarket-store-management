package com.supermarket.store.management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com"})
@EnableJpaRepositories(
        basePackages = {"com.supermarket.store.management.api.dao"})
@SpringBootApplication
public class SupermarketStoreManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermarketStoreManagementApiApplication.class, args);
    }

}
