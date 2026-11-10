package com.example.order.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@Configuration
public class OrderServiceConfig {

    @LoadBalanced // 开启负载均衡，注解式
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 开启OpenFeign远程调用的完整日志
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
