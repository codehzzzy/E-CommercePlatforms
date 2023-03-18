package com.demo.project.model.dto.Address;

/**
 * @author Rolin
 */
public class AddressAddRequest {
    private static final long serialVersionUID = 1L;

    //用户id
    private Long userId;

    //省级名称
    private String provinceName;

    //市级名称
    private String cityName;

    //区级名称
    private String districtName;

    //详细地址
    private String detail;
}
