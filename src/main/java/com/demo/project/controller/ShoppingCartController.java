package com.demo.project.controller;
import com.demo.project.annotation.AuthCheck;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.ErrorCode;
import com.demo.project.common.ResultUtils;
import com.demo.project.exception.BusinessException;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartAddRequest;
import com.demo.project.model.dto.ShoppingCart.ShoppingCartDeleteRequest;
import com.demo.project.model.entity.ShoppingCart;
import com.demo.project.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.demo.project.constant.UserConstant.DEFAULT_ROLE;

/**
 * 购物车接口
 *
 * @author hzzzzzy
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加到购物车
     *
     * @param shoppingCartAddRequest
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PostMapping("/add")
    public BaseResponse<ShoppingCart> add(@RequestBody ShoppingCartAddRequest shoppingCartAddRequest, HttpServletRequest request) {
        if (shoppingCartAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ShoppingCart shoppingCart = shoppingCartService.addShoppingCart(shoppingCartAddRequest,request);
        return ResultUtils.success(shoppingCart);
    }


    /**
     * 从购物车删除
     *
     * @param shoppingCartDeleteRequest
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PostMapping("/delete")
    public BaseResponse<ShoppingCart> delete(@RequestBody ShoppingCartDeleteRequest shoppingCartDeleteRequest) {
        ShoppingCart shoppingCart = shoppingCartService.deleteShoppingCart(shoppingCartDeleteRequest);
        return ResultUtils.success(shoppingCart);
    }

    /**
     * 查看购物车
     *
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @GetMapping("/list")
    public BaseResponse<List<ShoppingCart>> list(HttpServletRequest request){
        List<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCart(request);
        return ResultUtils.success(shoppingCart);
    }

    /**
     * 清空购物车
     *
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @DeleteMapping("/clean")
    public BaseResponse<Boolean> clean(HttpServletRequest request){
        Boolean flag = shoppingCartService.cleanShoppingCart(request);
        return ResultUtils.success(flag);
    }
}
