package com.example.order.feign;

import com.example.order.feign.fallback.ProductFeignClientFallback;
import com.example.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-10
 */
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class) // feign客户端
public interface ProductFeignClient {

    // mvc注解的两套使用逻辑
    // 1.标注在Controller上，是接受这样的请求
    // 2.标注在FeignClient上，是发起这样的请求
    @GetMapping("/api/product/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
