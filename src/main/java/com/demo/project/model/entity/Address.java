package com.demo.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址
 * @author Rolin
 */
@TableName(value ="address")
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    //用户id
    @TableField("user_id")
    private Long userId;

    //省级名称
    @TableField("province_name")
    private String provinceName;

    //市级名称
    @TableField("city_name")
    private String cityName;

    //区级名称
    @TableField("district_name")
    private String districtName;

    //详细地址
    private String detail;

    //是否默认 0 否 1是
    @TableField("is_default")
    private Integer isDefault;

    //创建时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    //是否删除
//    @TableLogic
//    @TableField("is_delete")
//    private Integer isDeleted;
}

