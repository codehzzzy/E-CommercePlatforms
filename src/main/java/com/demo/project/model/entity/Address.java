package com.demo.project.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址
 * @author Rolin
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

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

    //是否默认 0 否 1是
    private Integer isDefault;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //是否删除
    @TableLogic
    private Integer isDeleted;
}

