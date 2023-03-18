package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.common.ErrorCode;
import com.demo.project.exception.BusinessException;
import com.demo.project.mapper.OrderDetailMapper;
import com.demo.project.mapper.OrderMapper;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.Address;
import com.demo.project.model.entity.Order;
import com.demo.project.model.entity.OrderDetail;
import com.demo.project.model.entity.ShoppingCart;
import com.demo.project.service.AddressService;
import com.demo.project.service.OrderDetailService;
import com.demo.project.service.OrderService;
import com.demo.project.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author hzzzzzy
* @description 针对表【order(订单)】的数据库操作Service实现
* @createDate 2023-03-17 20:42:57
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService {
}




