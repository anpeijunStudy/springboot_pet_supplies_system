package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.Remark;

import java.util.List;

/**
 * 宠物用品关系表(Remark)表服务接口
 *
 * @author makejava
 * @since 2022-05-10 23:57:27
 */
public interface RemarkService {


    /**
     * 添加集合到备注表中pet_supplies_remark
     *
     * @param flavors 备注表信息
     * @return
     */
    boolean save(List<Remark> flavors);

    /**
     * 根据supplies给的ID查询备注信息
     *
     * @param queryWrapper 条件查询
     * @return
     */
    List<Remark> selectByIdList(LambdaQueryWrapper queryWrapper);

    /**
     * 根据ID删除信息
     *
     * @param queryWrapper
     * @return
     */
    Integer delete(LambdaQueryWrapper queryWrapper);
}
