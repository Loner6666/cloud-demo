package com.example.order.service;

import com.example.order.bean.Order;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
public interface OrderService {

    Order createOrder(Long productId, Long userId);
}
