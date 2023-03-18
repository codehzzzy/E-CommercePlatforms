package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.common.ErrorCode;
import com.demo.project.exception.BusinessException;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.Order;
import com.demo.project.model.entity.ShoppingCart;
import com.demo.project.service.AddressService;
import com.demo.project.service.OrderService;
import com.demo.project.mapper.OrderMapper;
import com.demo.project.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hzzzzzy
* @description 针对表【order(订单)】的数据库操作Service实现
* @createDate 2023-03-17 20:42:57
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @Override
    public void submit(OrderAddRequest orderAddRequest) {
        Order order = new Order();
        Long userId = orderAddRequest.getUserId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list();
        if(shoppingCarts == null || shoppingCarts.size()==0){
            throw new BusinessException(ErrorCode.DATA_NULL_ERROR,"购物车中没有数据");
        }
        Long addressId = orderAddRequest.getAddressId();

    }
}




