package com.example.order.config;

import feign.Logger;
import feign.Retryer;
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

    /**
     * 开启OpenFeign的重试机制
     * 默认：(100L, TimeUnit.SECONDS.toMillis(1L), 5)
     * 也可手动指定：(long period, long maxPeriod, int maxAttempts)
     * period：重试间隔时间
     * maxPeriod：最大重试间隔时间
     * maxAttempts：最大重试次数
     */
//    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }

}
