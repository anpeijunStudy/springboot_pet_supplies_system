package com.cn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 宠物用品关系表(Remark)实体类
 *
 * @author makejava
 * @since 2022-05-10 23:57:26
 */
@Data
@TableName("pet_supplies_remark")
public class Remark implements Serializable {
    private static final long serialVersionUID = 976597306068973250L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 宠物
     */
    private Long petSuppliesId;
    /**
     * 宠物用品备注项目
     */
    private String name;
    /**
     * 宠物用品备注项目具体
     */
    private String value;
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

