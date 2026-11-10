package com.example.order;

import com.example.order.feign.WeatherFeignClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description：测试OpenFeign远程调用第三方API（墨迹天气）
 * @Author：GuoFeng
 * @CreateTime：2026-11-10
 */
@SpringBootTest
public class WeatherTest {

    @Resource
    private WeatherFeignClient weatherFeignClient;

    @Test
    public void testWeather() {
        String weather = weatherFeignClient.getWeather("APPCODE 93b7e19861a24c519a7548b17dc16d75",
                "50b53ff8dd7d9fa320d3d3ca32cf8ed1",
                "5"); // 北京市昌平区
        System.out.println("weather = " + weather);
    }
}
