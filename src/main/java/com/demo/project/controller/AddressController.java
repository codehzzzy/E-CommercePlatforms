package com.demo.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.project.annotation.AuthCheck;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.ResultUtils;
import com.demo.project.model.dto.Address.AddressAddRequest;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.Address;
import com.demo.project.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.project.constant.UserConstant.ADMIN_ROLE;
import static com.demo.project.constant.UserConstant.DEFAULT_ROLE;

/**
 * @author Rolin
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PostMapping("/save")
    public BaseResponse<String> save(@RequestBody Address address) {
        addressService.save(address);
        return ResultUtils.success("新增地址成功");
    }

    /**
     * 设置默认地址
     * @param address
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PutMapping("/default")
    @Transactional
    public BaseResponse<String> setDefault(@RequestBody Address address) {
        LambdaUpdateWrapper<Address> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Address::getUserId,address.getUserId());
        wrapper.set(Address::getIsDefault,0);
        addressService.update(wrapper);

        address.setIsDefault(1);
        addressService.updateById(address);
        return ResultUtils.success("设置默认地址成功");
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @DeleteMapping("/delete")
    public BaseResponse<String> delete(Long id) {
        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Address::getId,id);
        addressService.remove(lambdaQueryWrapper);
        return ResultUtils.success("删除地址成功");
    }

    /**
     * 更新地址
     * @param address
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PutMapping("/update")
    @Transactional
    public BaseResponse<String> update(@RequestBody Address address) {
        addressService.updateById(address);
        return ResultUtils.success("修改地址成功");
    }

    /**
     * 获取全部地址
     * @param id
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @GetMapping("/list")
    public BaseResponse<List<Address>> list(Long id) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,Address::getUserId,id);
        queryWrapper.orderByDesc(Address::getUpdateTime);
        return ResultUtils.success(addressService.list(queryWrapper));
    }
}
