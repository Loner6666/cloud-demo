package com.example.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.order.bean.Order;
import com.example.order.feign.ProductFeignClient;
import com.example.order.service.OrderService;
import com.example.product.bean.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    @Resource // 负载均衡，一定导入 spring-cloud-starter-loadbalancer 场景
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private ProductFeignClient productFeignClient;


    @SentinelResource(value = "createOrder")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);

        // 使用Feign完成远程调用
        Product product = productFeignClient.getProductById(productId);
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
     * 缺点：请求过于集中，需要自己写负载算法
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

    /**
     * 远程调用商品服务，获取商品信息
     * 进阶2：完成负载均衡发送请求
     * 缺点：还需要拼接 ip+port，不够优雅
     */
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // 1. 获取到商品服务所在的所有机器IP+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        // 远程URL地址 http://localhost:9000/product/4
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程调用商品服务，URL地址：{}", url);

        // 2. 给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    /**
     * 远程调用商品服务，获取商品信息
     * 进阶3：基于注解的负载均衡，在 RestTemplate 上加 @LoadBalanced 注解
     * 优点：不需要拼接 ip+port，只需要写服务名即可
     */
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        log.info("远程调用商品服务，URL地址：{}", url);

        // 2. 给远程发送请求； service-product 会被动态替换
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }


}
