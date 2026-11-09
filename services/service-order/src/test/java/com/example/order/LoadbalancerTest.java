package com.example.order;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 * @Description：测试 loadBalancer 负载均衡
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@SpringBootTest
public class LoadbalancerTest {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private DiscoveryClient discoveryClient;

    @Test
    public void loadBalancerClientTest() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        System.out.println("choose = " + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("choose = " + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("choose = " + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("choose = " + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("choose = " + choose.getHost() + ":" + choose.getPort());

    }
}
