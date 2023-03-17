package com.demo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.DeleteRequest;
import com.demo.project.common.ErrorCode;
import com.demo.project.exception.BusinessException;
import com.demo.project.mapper.ProductMapper;
import com.demo.project.model.dto.product.ProductAddRequest;
import com.demo.project.model.dto.product.ProductUpdateRequest;
import com.demo.project.model.entity.Product;
import com.demo.project.model.entity.User;
import com.demo.project.model.vo.ProductDetailedVO;
import com.demo.project.model.vo.ProductVO;
import com.demo.project.service.ProductService;
import com.demo.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.demo.project.constant.CommonConstant.ASSIST_IN_JUDGMENT;

/**
* @author hzzzzzy
* @description 针对表【product(商品)】的数据库操作Service实现
* @createDate 2023-03-17 20:28:15
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService{
    @Autowired
    private UserService userService;

    /**
     * 创建商品
     *
     * @param productAddRequest
     * @param request
     * @return
     */
    @Override
    public Long addProduct(ProductAddRequest productAddRequest, HttpServletRequest request) {
        if (productAddRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productAddRequest,product);
        User user = userService.getLoginUser(request);
        if (user == null){
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        return user.getId();
    }


    /**
     * 更新商品
     *
     * @param productUpdateRequest
     * @param request
     * @return
     */
    @Override
    public Boolean updateProduct(ProductUpdateRequest productUpdateRequest, HttpServletRequest request) {
        if (productUpdateRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productUpdateRequest,product);
        boolean flag = this.save(product);
        if (!flag){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新失败");
        }
        return flag;
    }


    /**
     * 通过商品的 id 获取商品的具体信息
     *
     * @param id
     * @return
     */
    @Override
    public ProductDetailedVO getDetailedById(Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductDetailedVO productDetailedVO = new ProductDetailedVO();
        Product product = this.getById(id);
        if (product == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不存在该商品信息");
        }
        BeanUtils.copyProperties(product,productDetailedVO);
        return productDetailedVO;
    }


    /**
     * 通过商品的 id 删除商品信息
     *
     * @param deleteRequest
     * @return
     */
    @Override
    public Boolean delete(DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean flag = this.removeById(deleteRequest.getId());
        if (!flag){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"删除信息失败");
        }
        return flag;
    }


    /**
     * 分页获取所有商品的简略信息列表
     *
     * @param current
     * @param size
     * @return
     */
    @Override
    public Page<ProductVO> getAll(long current, long size) {
        // 创建分页构造器对象
        Page<ProductVO> dtoPage = new Page<>();
        Page<Product> pageInfo = new Page<>(current,size);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        this.page(pageInfo,queryWrapper);
        // 对象拷贝
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        List<ProductVO> productVOList = pageInfo.getRecords().stream().map((product) -> {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            return productVO;
        }).collect(Collectors.toList());
        dtoPage.setRecords(productVOList);
        return dtoPage;
    }


    /**
     * 多条件动态查询商品
     *
     * @param name
     * @param description
     * @param price_pre
     * @param price_suf
     * @param flag
     * @return
     */
    @Override
    public List<ProductVO> searchBy(String name, String description, BigDecimal price_pre, BigDecimal price_suf,Boolean flag) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        queryWrapper.like(StringUtils.isNotBlank(name),Product::getName,name);
        queryWrapper.like(StringUtils.isNotBlank(description),Product::getDescription,description);
        queryWrapper.ge(flag,Product::getInventory,0);
        if (price_pre != null){
            queryWrapper.ge(price_pre.compareTo(ASSIST_IN_JUDGMENT) > -1,Product::getPrice,price_pre);
        }
        if (price_suf != null){
            queryWrapper.le(price_suf.compareTo(ASSIST_IN_JUDGMENT) > -1,Product::getPrice,price_suf);
        }
        List<Product> pigList = this.list(queryWrapper);
        List<ProductVO> productVOList = new ArrayList<>();
        pigList.forEach((product)->{
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);
            productVOList.add(productVO);
        });
        if (productVOList == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"动态查询商品失败");
        }
        return productVOList;
    }
}




