package com.demo.project.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.project.annotation.AuthCheck;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.DeleteRequest;
import com.demo.project.common.ErrorCode;
import com.demo.project.common.ResultUtils;
import com.demo.project.exception.BusinessException;
import com.demo.project.model.dto.product.ProductAddRequest;
import com.demo.project.model.dto.product.ProductUpdateRequest;
import com.demo.project.model.vo.ProductDetailedVO;
import com.demo.project.model.vo.ProductVO;
import com.demo.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.demo.project.constant.UserConstant.ADMIN_ROLE;
import static com.demo.project.constant.UserConstant.DEFAULT_ROLE;

/**
 * 商品接口
 *
 * @author hzzzzzy
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 创建商品
     *
     * @param productAddRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/add")
    public BaseResponse<Long> addProduct(@RequestBody ProductAddRequest productAddRequest, HttpServletRequest request) {
        if (productAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = productService.addProduct(productAddRequest,request);
        return ResultUtils.success(userId);
    }


    /**
     * 更新商品
     *
     * @param productUpdateRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest, HttpServletRequest request) {
        if (productUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean flag = productService.updateProduct(productUpdateRequest, request);
        return ResultUtils.success(flag);
    }


    /**
     * 通过商品的 id 获取商品的具体信息
     *
     * @param id
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @GetMapping("/getById")
    public BaseResponse<ProductDetailedVO> getById(Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductDetailedVO productDetailedVO = productService.getDetailedById(id);
        return ResultUtils.success(productDetailedVO);
    }


    /**
     * 通过商品的 id 删除商品信息
     *
     * @param deleteRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(DeleteRequest deleteRequest) {
        Boolean flag = productService.delete(deleteRequest);
        return ResultUtils.success(flag);
    }


    /**
     * 分页获取所有商品的简略信息列表
     *
     * @param current
     * @param size
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @GetMapping("/getAll/{current}/{size}")
    public BaseResponse<Page<ProductVO>> getAll(@PathVariable long current, @PathVariable long size){
        Page<ProductVO> productVO = productService.getAll(current, size);
        return ResultUtils.success(productVO);
    }


    /**
     * 多条件动态查询商品
     *
     * @param name
     * @param description
     * @param price_pre 价格起始值
     * @param price_suf 价格结束值
     * @param flag 是否查询有库存的商品
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @GetMapping("/searchBy/{flag}")
    public BaseResponse<List<ProductVO>> searchBy(String name, String description, BigDecimal price_pre, BigDecimal price_suf,@PathVariable Boolean flag){
        List<ProductVO> productVOList = productService.searchBy(name,description,price_pre,price_suf,flag);
        return ResultUtils.success(productVOList);
    }
}
