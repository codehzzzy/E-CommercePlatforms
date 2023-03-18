package com.demo.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 购物车
 * @TableName shopping_cart
 */
@TableName(value ="shopping_cart")
@Data
public class ShoppingCart implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 商品图片url
     */
    private String url;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}