package com.example.order.service.impl;

import com.example.order.bean.Order;
import com.example.order.service.OrderService;
import com.example.product.bean.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private RestTemplate restTemplate;


    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = getProductFromRemote(productId);
        Order order = new Order();
        order.setId(1L);
        // 总金额 = 单价 * 数量
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("北京市昌平区");
        // 远程查询商品列表
        order.setProductList(Arrays.asList(product));

        return order;
    }

    /**
     * 远程调用商品服务，获取商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    private Product getProductFromRemote(Long productId) {
        // 1. 获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 远程URL地址 http://localhost:9000/product/4
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程调用商品服务，URL地址：{}", url);

        // 2. 给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }


}
