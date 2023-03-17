package com.demo.project.model.dto.ShoppingCart;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车删除请求
 */
@Data
public class ShoppingCartDeleteRequest implements Serializable {

    /**
     * 商品id
     */
    private Long productId;

    private static final long serialVersionUID = 1L;
}