package com.demo.project.model.vo;

import com.demo.project.model.entity.Order;
import com.demo.project.model.entity.OrderDetail;
import lombok.Data;

import java.util.List;

/**
 * @author Rolin
 */
@Data
public class OrderVo extends Order {

    //记录订单的数量
    private Integer sumNum;

    //记录下单用户的名字
    private String consignee;

    //记录订单中具体的商品
    private List<OrderDetail> orderDetails;
}
