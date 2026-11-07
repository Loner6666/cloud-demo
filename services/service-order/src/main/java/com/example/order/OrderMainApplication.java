package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-07
 */
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
        System.out.println("OrderMainApplication start up success!");
    }
}
