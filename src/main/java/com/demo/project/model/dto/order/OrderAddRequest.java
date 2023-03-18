package com.demo.project.model.dto.order;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单创建请求
 */
@Data
public class OrderAddRequest implements Serializable {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 地址id
     */
    private Long addressId;

    /**
     * 下单时间
     */
    private LocalDate orderTime;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 购买数量
     */
    private Long productNumber;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;

    /**
     * 支付方式 1微信，2支付宝
     */
    private Integer method;

    /**
     * 订单总价
     */
    private BigDecimal orderPrice;

    private static final long serialVersionUID = 1L;
}