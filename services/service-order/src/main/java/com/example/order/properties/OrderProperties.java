package com.example.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description： nacos动态配置类
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@Component
@ConfigurationProperties(prefix = "order") // 配置批量绑定在nacos下，可以无需 @RefreshScope 就能实现自动刷新
@Data
public class OrderProperties {

    private String timeout;

    private String autoConfirm;
}
