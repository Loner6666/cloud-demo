package com.example.product.controller;

import com.example.product.bean.Product;
import com.example.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 获取商品信息
     */
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        return product;
    }
}
