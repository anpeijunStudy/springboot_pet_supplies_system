package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Supplies;

/**
 * 宠物用品管理(Supplies)表服务接口
 *
 * @author makejava
 * @since 2022-05-10 20:12:41
 */
public interface SuppliesService {

    /**
     * 统计CategoryID的count
     *
     * @param suppliesLQW
     * @return
     */
    Integer count(LambdaQueryWrapper<Supplies> suppliesLQW);

    /**
     * 新增菜品（同时操作两张表pet_supplies和 pet_supplies_reamrk）
     *
     * @param suppliesDto 数据
     * @return
     */
    boolean saveWithRemark(SuppliesDto suppliesDto);

    /**
     * 分页查询
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    Page page(Page page, LambdaQueryWrapper<Supplies> queryWrapper);

    /**
     * 根据用户ID查询用户表和其对应的备注信息
     *
     * @param id
     * @return
     */
    SuppliesDto getByIDCatchWithRemark(Long id);

    /**
     * 修改数据
     *
     * @param suppliesDto
     * @return
     */
    boolean updateWithRemark(SuppliesDto suppliesDto);

    /**
     * 批量删除用品（需要同时删除信息表）
     * @param ids
     * @return
     */
    boolean delete(Integer[] ids);
}
