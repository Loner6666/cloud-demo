package com.example.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-10
 */
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * OpenFeign的请求拦截器（写在这里全部远程服务调用都生效）
     *
     * @param requestTemplate 请求模板
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("XTokenRequestInterceptor start 。。。。");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());

    }
}
