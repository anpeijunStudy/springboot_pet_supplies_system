package com.cn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 团购用品关系(SetmealSupplies)实体类
 *
 * @author makejava
 * @since 2022-05-12 16:27:47
 */
@Data
@TableName("pet_setmeal_supplies")
public class SetmealSupplies implements Serializable {
    private static final long serialVersionUID = -37561867735153799L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 团购id
     */
    private String setmealId;
    /**
     * 用品id
     */
    private String suppliesId;
    /**
     * 用品名称
     */
    private String name;
    /**
     * 用品原价
     */
    private Double price;
    /**
     * 份数
     */
    private Integer copies;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)//插入填充字段
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新填充字段
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)//插入填充字段
    private Long createUser;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新填充字段
    private Long updateUser;
    /**
     * 是否删除
     */
    private Integer isDeleted;

}

