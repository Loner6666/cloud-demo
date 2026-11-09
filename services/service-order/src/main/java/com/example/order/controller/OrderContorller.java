package com.example.order.controller;

import com.example.order.bean.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
//@RefreshScope // Nacos动态刷新配置
@RestController
public class OrderContorller {

    @Resource
    private OrderService orderService;

//    @Value("${order.timeout}")
//    private String orderTimeout;
//    @Value("${order.auto-confirm}")
//    private String orderAutoConfirm;

    @Resource
    private OrderProperties orderProperties;

    /**
     * 获取动态配置信息
     */
    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=" + orderProperties.getTimeout() + "；order.auto-confirm=" + orderProperties.getAutoConfirm();
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
