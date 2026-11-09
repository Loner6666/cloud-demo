package com.example.product.service;

import com.example.product.bean.Product;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
public interface ProductService {
    Product getProductById(Long productId);
}
