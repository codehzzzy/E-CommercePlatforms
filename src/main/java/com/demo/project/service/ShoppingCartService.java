package com.demo.project.service;

import com.demo.project.common.BaseResponse;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartAddRequest;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartDeleteRequest;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hzzzzzy
* @description 针对表【shopping_cart(购物车)】的数据库操作Service
* @createDate 2023-03-17 20:28:15
*/
public interface ShoppingCartService extends IService<ShoppingCart> {


    /**
     * 添加到购物车
     *
     * @param shoppingCartAddRequest
     * @param request
     * @return
     */
    ShoppingCart addShoppingCart(ShoppingCartAddRequest shoppingCartAddRequest, HttpServletRequest request);

    /**
     * 从购物车删除
     *
     * @param shoppingCartDeleteRequest
     * @return
     */
    ShoppingCart deleteShoppingCart(ShoppingCartDeleteRequest shoppingCartDeleteRequest);


    /**
     * 查看购物车
     *
     * @param request
     * @return
     */
    List<ShoppingCart> getShoppingCart(HttpServletRequest request);


    /**
     * 清空购物车
     *
     * @param request
     * @return
     */
    Boolean cleanShoppingCart(HttpServletRequest request);

}
