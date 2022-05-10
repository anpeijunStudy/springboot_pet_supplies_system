package com.cn.service.impl;

import com.cn.entity.Setmeal;
import com.cn.dao.SetmealDao;
import com.cn.service.SetmealService;
import org.springframework.stereotype.Service;

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

}
