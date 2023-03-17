package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.ErrorCode;
import com.demo.project.exception.BusinessException;
import com.demo.project.mapper.ShoppingCartMapper;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartAddRequest;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartDeleteRequest;
import com.demo.project.model.entity.ShoppingCart;
import com.demo.project.model.entity.User;
import com.demo.project.service.ShoppingCartService;
import com.demo.project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @author hzzzzzy
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2023-03-17 20:28:15
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService{
    @Autowired
    private UserService userService;

    /**
     * 获取当前用户id
     *
     * @param request
     */
    private Long getCurrentUserId(HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return loginUser.getId();
    }

    /**
     * 添加到购物车
     *
     * @param shoppingCartAddRequest
     * @param request
     * @return
     */
    @Override
    public Boolean addShoppingCart(ShoppingCartAddRequest shoppingCartAddRequest, HttpServletRequest request) {
        Boolean flag;
        Long userId = getCurrentUserId(request);
        shoppingCartAddRequest.setUserId(userId);
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        // 查询是否在购物车中
        Long productId = shoppingCartAddRequest.getProductId();
        if (productId != null){
            queryWrapper.eq(ShoppingCart::getProductId,productId);
        }
        // 查询出来的购物车信息
        ShoppingCart queryShoppingCart = this.getOne(queryWrapper);
        if (queryShoppingCart != null){
            //已经存在，就在原来数量基础上加1
            Integer number = shoppingCartAddRequest.getNumber();
            queryShoppingCart.setNumber(number+1);
            flag = this.updateById(queryShoppingCart);
        }else {
            //不存在，添加到购物车
            shoppingCartAddRequest.setNumber(1);
            ShoppingCart shoppingCart = new ShoppingCart();
            BeanUtils.copyProperties(shoppingCartAddRequest,shoppingCart);
            flag = this.save(shoppingCart);
        }
        return flag;
    }


    /**
     * 从购物车删除
     *
     * @param shoppingCartDeleteRequest
     * @return
     */
    @Override
    public ShoppingCart deleteShoppingCart(ShoppingCartDeleteRequest shoppingCartDeleteRequest) {
        Long productId = shoppingCartDeleteRequest.getProductId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getProductId,productId);
        ShoppingCart shoppingCart = this.getOne(queryWrapper);
        Integer number = shoppingCart.getNumber();
        if (number > 0){
            shoppingCart.setNumber(shoppingCart.getNumber()-1);
            this.update(shoppingCart,queryWrapper);
        }else {
            this.remove(queryWrapper);
        }
        return shoppingCart;
    }

    /**
     * 查看购物车
     *
     * @param request
     * @return
     */
    @Override
    public List<ShoppingCart> getShoppingCart(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        queryWrapper.orderByAsc(ShoppingCart::getId);
        List<ShoppingCart> list = this.list(queryWrapper);
        if (list == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"查看购物车失败");
        }
        return list;
    }


    /**
     * 清空购物车
     *
     * @param request
     * @return
     */
    @Override
    public Boolean cleanShoppingCart(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        boolean flag = this.remove(queryWrapper);
        if (!flag){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"清空购物车失败");
        }
        return flag;
    }
}




