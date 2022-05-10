package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.Setmeal;
import com.cn.entity.Supplies;

/**
 * 团购(Setmeal)表服务接口
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
public interface SetmealService {


    /**
     * 查询CategroyId的Count
     * @param setmealLQW
     * @return
     */
    Integer count(LambdaQueryWrapper<Setmeal> setmealLQW);
}
