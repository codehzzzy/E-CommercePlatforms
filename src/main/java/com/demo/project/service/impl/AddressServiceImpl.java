package com.demo.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.mapper.AddressMapper;
import com.demo.project.mapper.OrderMapper;
import com.demo.project.model.entity.Address;
import com.demo.project.model.entity.Order;
import com.demo.project.service.AddressService;
import com.demo.project.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Rolin
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {

}
