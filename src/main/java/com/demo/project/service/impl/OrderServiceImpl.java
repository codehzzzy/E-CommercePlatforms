package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.common.ErrorCode;
import com.demo.project.exception.BusinessException;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.*;
import com.demo.project.service.*;
import com.demo.project.mapper.OrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author hzzzzzy
* @description 针对表【order(订单)】的数据库操作Service实现
* @createDate 2023-03-17 20:42:57
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void submit(OrderAddRequest orderAddRequest) {
        Long userId = orderAddRequest.getUserId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list();
        if(shoppingCarts == null || shoppingCarts.size()==0){
            throw new BusinessException(ErrorCode.DATA_NULL_ERROR,"购物车中没有数据");
        }

        User user = userService.getById(orderAddRequest.getUserId());

        Long addressId = orderAddRequest.getAddressId();
        Address address = addressService.getById(addressId);
        if(address==null){
            throw new BusinessException(ErrorCode.DATA_NULL_ERROR,"没有地址数据");
        }

        long orderId = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);

        Order orders = new Order();

        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) ->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getPrice());
            amount.addAndGet(item.getPrice().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());


        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        //总金额
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(orderAddRequest.getUserId());
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getUsername());
        orders.setConsignee(orderAddRequest.getConsignee());
        orders.setPhone(user.getPhone());
        orders.setAddress((address.getProvinceName() == null ? "" : address.getProvinceName())
                + (address.getCityName() == null ? "" : address.getCityName())
                + (address.getDistrictName() == null ? "" : address.getDistrictName())
                + (address.getDetail() == null ? "" : address.getDetail()));

        //向订单表插入一条数据
        this.save(orders);

        //向订单明细表中插入一条或多条数据
        orderDetailService.saveBatch(orderDetails);

        //清空购物车数据
        shoppingCartService.remove(queryWrapper);
    }
}




