package com.cn.service;

import com.cn.entity.SetmealSupplies;

import java.util.List;

/**
 * 团购用品关系(SetmealSupplies)表服务接口
 *
 * @author makejava
 * @since 2022-05-12 16:27:47
 */
public interface SetmealSuppliesService {

    /**
     * 添加团购信息表
     *
     * @param setmealSuppliesList
     * @return
     */
    boolean saveBatch(List<SetmealSupplies> setmealSuppliesList);

    /**
     * 根据ids删除数据
     *
     * @param ids
     * @return
     */
    boolean deleteBySetmealIds(Long[] ids);
}
