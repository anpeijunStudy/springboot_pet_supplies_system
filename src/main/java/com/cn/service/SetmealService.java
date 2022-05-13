package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dto.SetmealDto;
import com.cn.entity.Setmeal;

/**
 * 团购(Setmeal)表服务接口
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
public interface SetmealService {


    /**
     * 查询CategroyId的Count
     *
     * @param setmealLQW
     * @return
     */
    Integer count(LambdaQueryWrapper<Setmeal> setmealLQW);

    /**
     * 添加数据到团购表和团购信息表
     *
     * @param stemealDto
     * @return
     */
    boolean saveWithSupplies(SetmealDto stemealDto);

    /**
     * 查询分页数据
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Page page(Integer page, Integer pageSize, String name);

    /**
     * 删除团购表数据
     *
     * @param ids
     * @return
     */
    boolean deleteWithSupplies(Long[] ids);
}
