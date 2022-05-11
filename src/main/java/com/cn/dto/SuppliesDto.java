package com.cn.dto;

import com.cn.entity.Remark;
import com.cn.entity.Supplies;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 安珮军
 * @version 1.0
 * @Description: DTO 全称为Data Transfer Object，即数据传输对象，一般用于展示层和服务层之间的数据传输
 */
@Data
public class SuppliesDto extends Supplies {

    /**
     * 用来封装remark数据（用品的备注）
     */
    private List<Remark> flavors = new ArrayList<>();

    /**
     * 扩展的属性用来与前端的用品名称 交互
     */
    private String categoryName;

    private Integer copies;
}
