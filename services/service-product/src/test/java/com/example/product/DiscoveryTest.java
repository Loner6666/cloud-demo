package com.example.product;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@SpringBootTest
public class DiscoveryTest {
    // 所有注册中心都可以用
    @Resource
    private DiscoveryClient discoveryClient;

    // 只适用于nacos注册中心
    @Resource
    private NacosDiscoveryClient nacosDiscoveryClient;

    @Test
    public void discoveryClientTest() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("service = " + service);
            // 获取ip+port
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip:" + instance.getHost() + "；" + "port:" + instance.getPort());
            }
        }
    }

    @Test
    public void nacosDiscoveryClientTest() {
        for (String service : nacosDiscoveryClient.getServices()) {
            System.out.println("service = " + service);
            // 获取ip+port
            List<ServiceInstance> instances = nacosDiscoveryClient.getInstances(service);
            for (ServiceInstance instance : instances)
                System.out.println("ip:" + instance.getHost() + "；" + "port:" + instance.getPort());
        }
    }
}
