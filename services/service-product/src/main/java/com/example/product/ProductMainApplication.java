package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-07
 */
@EnableDiscoveryClient // 开启服务注册与发现功能
@SpringBootApplication
public class ProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMainApplication.class, args);
        System.out.println("ProductMainApplication start up success!");
    }
}
