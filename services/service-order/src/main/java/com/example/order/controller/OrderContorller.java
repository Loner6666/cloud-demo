package com.example.order.controller;

import com.example.order.bean.Order;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@RefreshScope // Nacos动态刷新配置
@RestController
public class OrderContorller {

    @Resource
    private OrderService orderService;

    @Value("${order.timeout}")
    private String orderTimeout;

    @Value("${order.auto-confirm}")
    private String orderAutoConfirm;

    /**
     * 获取动态配置信息
     */
    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=" + orderTimeout + "；order.auto-confirm=" + orderAutoConfirm;
    }

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }
}
