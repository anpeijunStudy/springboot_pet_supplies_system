package com.cn.service.impl;

import com.cn.entity.SetmealSupplies;
import com.cn.dao.SetmealSuppliesDao;
import com.cn.service.SetmealSuppliesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 团购用品关系(SetmealSupplies)表服务实现类
 *
 * @author makejava
 * @since 2022-05-12 16:27:47
 */
@Service("setmealSuppliesService")
public class SetmealSuppliesServiceImpl implements SetmealSuppliesService {
    @Resource
    private SetmealSuppliesDao setmealSuppliesDao;

}
