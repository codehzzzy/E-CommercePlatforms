package com.demo.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.model.entity.Order;
import com.demo.project.service.OrderService;
import com.demo.project.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author hzzzzzy
* @description 针对表【order(订单)】的数据库操作Service实现
* @createDate 2023-03-17 20:42:57
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




