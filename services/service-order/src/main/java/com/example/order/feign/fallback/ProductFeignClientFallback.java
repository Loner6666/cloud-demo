package com.example.order.feign.fallback;

import com.example.order.feign.ProductFeignClient;
import com.example.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description：OpenFeign 远程调用商品服务，失败时的兜底回调处理
 * @Author：GuoFeng
 * @CreateTime：2026-11-10
 */
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调~~~");
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("未知商品");
        product.setNum(0);

        return product;
    }
}
