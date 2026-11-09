package com.example.order.config;

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
}
