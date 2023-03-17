package com.demo.project.model.dto.ShoppingCart;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车新增请求
 */
@Data
public class ShoppingCartAddRequest implements Serializable {
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