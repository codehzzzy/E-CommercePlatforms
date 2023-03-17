package com.demo.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}