package com.example.order.bean;

import com.example.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description：订单对象
 * @Author：GuoFeng
 * @CreateTime：2026-11-09
 */
@Data
public class Order {
    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 下单用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 派送地址
     */
    private String address;
    /**
     * 商品列表
     */
    private List<Product> productList;
}
