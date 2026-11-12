package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.order.bean.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
//@RequestMapping("/api/order")
@Slf4j
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
     * http://localhost:8000/config
     */
    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=" + orderProperties.getTimeout() + "；" +
                "order.auto-confirm=" + orderProperties.getAutoConfirm() + "；" +
                "order.db-url=" + orderProperties.getDbUrl();
    }

    /**
     * 创建订单
     * http://localhost:8000/create?userId=2&productId=100
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }


    /**
     * 秒杀订单
     * http://localhost:8000/seckill?userId=2&productId=100
     */
    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId", required = false) Long userId,
                         @RequestParam(value = "productId", defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    /**
     * seckill的降级方法
     * blockHandler 只能处理 BlockException 异常，回调方法中最后一个参数只能是 BlockException 类型的异常，否则无法生效
     * fallback 可以处理任何异常，回调方法中的最后一个参数是 Throwable 类型的异常
     */
    public Order seckillFallback(Long userId, Long productId, Throwable exception) {
        System.out.println("seckillFallback ~~~");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常信息：" + exception.getClass());
        return order;
    }

    /**
     * 模拟写数据
     * http://localhost:8000/writeDb
     */
    @GetMapping("/writeDb")
    public String writeDb() {
        return "writeDb success ~";
    }

    /**
     * 模拟读数据
     * http://localhost:8000/readDb
     */
    @GetMapping("/readDb")
    public String readDb() {
        log.info("readDb");
        return "readDb success ~";
    }

}
