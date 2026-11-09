package com.example.product.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description：商品实体对象
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@Data
public class Product {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 购买数量
     */
    private int num;
}
