package com.demo.project.model.dto.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 商品更新请求
 * @author Rolin
 */
@Data
public class ProductUpdateRequest implements Serializable {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品id
     */
    private Long id;

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

    /**
     * 是否删除
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
