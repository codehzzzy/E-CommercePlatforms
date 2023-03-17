package com.demo.project.model.dto.product;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品创建请求
 */
@Data
public class ProductAddRequest implements Serializable {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品图片url
     */
    private String url;

    /**
     * 商品库存
     */
    private Long inventory;

    private static final long serialVersionUID = 1L;
}
