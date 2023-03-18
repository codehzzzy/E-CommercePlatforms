package com.demo.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.project.common.BaseResponse;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hzzzzzy
* @description 针对表【order(订单)】的数据库操作Service
* @createDate 2023-03-17 20:42:57
*/
public interface OrderService extends IService<Order> {
    void submit(OrderAddRequest orderAddRequest);

    BaseResponse<Page> orderPage(OrderAddRequest orderAddRequest, int page, int size);
}
