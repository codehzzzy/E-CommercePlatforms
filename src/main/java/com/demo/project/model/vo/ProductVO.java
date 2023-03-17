package com.demo.project.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


/**
 * 商品简略视图
 */
@Data
public class ProductVO implements Serializable {
    /**
     * 商品id
     */
    private Long id;

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
