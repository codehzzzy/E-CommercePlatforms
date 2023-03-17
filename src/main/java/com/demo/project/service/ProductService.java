package com.demo.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.project.common.DeleteRequest;
import com.demo.project.model.dto.product.ProductAddRequest;
import com.demo.project.model.dto.product.ProductUpdateRequest;
import com.demo.project.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.project.model.vo.ProductDetailedVO;
import com.demo.project.model.vo.ProductVO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
* @author hzzzzzy
* @description 针对表【product(商品)】的数据库操作Service
* @createDate 2023-03-17 20:28:15
*/
public interface ProductService extends IService<Product> {


    /**
     * 创建商品
     *
     * @param productAddRequest
     * @return
     */
    Long addProduct(ProductAddRequest productAddRequest);


    /**
     * 更新商品
     *
     * @param productUpdateRequest
     * @param request
     * @return
     */
    Boolean updateProduct(ProductUpdateRequest productUpdateRequest, HttpServletRequest request);


    /**
     * 通过商品的 id 获取商品的具体信息
     *
     * @param id
     * @return
     */
    ProductDetailedVO getDetailedById(Long id);


    /**
     * 通过商品的 id 删除商品信息
     *
     * @param deleteRequest
     * @return
     */
    Boolean delete(DeleteRequest deleteRequest);


    /**
     * 分页获取所有商品的简略信息列表
     *
     * @param current
     * @param size
     * @return
     */
    Page<ProductVO> getAll(long current, long size);


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
    List<ProductVO> searchBy(String name, String description, BigDecimal price_pre, BigDecimal price_suf,Boolean flag);
}
