package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.dto.StemealDto;
import com.cn.entity.Setmeal;
import com.cn.dao.SetmealDao;
import com.cn.entity.Supplies;
import com.cn.service.SetmealService;
import com.cn.service.SetmealSuppliesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 团购(Setmeal)表服务实现类
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@Service("setmealService")
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealDao setmealDao;

    @Resource
    private SetmealSuppliesService setmealSuppliesService;

    @Override
    public Integer count(LambdaQueryWrapper<Setmeal> setmealLQW) {
        return setmealDao.selectCount(setmealLQW);
    }

    @Override
    @Transactional
    public boolean saveWithSupplies(StemealDto stemealDto) {

        // 保存基本团购信息
        int insert = setmealDao.insert(stemealDto);
        // 保存团购和宠物用品的关联信息

        return false;
    }
}
