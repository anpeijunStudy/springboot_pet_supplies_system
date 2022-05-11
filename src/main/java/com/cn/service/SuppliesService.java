package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
     *  新增菜品（同时操作两张表pet_supplies和 pet_supplies_reamrk）
     * @param suppliesDto 数据
     * @return
     */
    boolean saveWithRemark(SuppliesDto suppliesDto);
}
