package com.demo.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.project.annotation.AuthCheck;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.DeleteRequest;
import com.demo.project.common.ErrorCode;
import com.demo.project.common.ResultUtils;
import com.demo.project.exception.BusinessException;
import com.demo.project.model.dto.order.OrderAddRequest;
import com.demo.project.model.dto.product.ProductAddRequest;
import com.demo.project.model.dto.product.ProductUpdateRequest;
import com.demo.project.model.vo.ProductDetailedVO;
import com.demo.project.model.vo.ProductVO;
import com.demo.project.service.OrderService;
import com.demo.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static com.demo.project.constant.UserConstant.ADMIN_ROLE;
import static com.demo.project.constant.UserConstant.DEFAULT_ROLE;

/**
 * 订单接口
 *
 * @author hzzzzzy
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param orderAddRequest
     * @return
     */
    @AuthCheck(mustRole = DEFAULT_ROLE)
    @PostMapping("/submit")
    public BaseResponse<Long> addOrder(@RequestBody OrderAddRequest orderAddRequest) {
        orderService.submit(orderAddRequest);
        return null;
    }
}
