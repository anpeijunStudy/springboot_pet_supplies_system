package com.cn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 团购(Setmeal)实体类
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@Data
public class Setmeal implements Serializable {
    private static final long serialVersionUID = 294721473043482407L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 宠物用品分类id
     */
    private Long categoryId;
    /**
     * 团购名称
     */
    private String name;
    /**
     * 团购价格
     */
    private Double price;
    /**
     * 状态 0:停用 1:启用
     */
    private Integer status;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 图片
     */
    private String image;
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

