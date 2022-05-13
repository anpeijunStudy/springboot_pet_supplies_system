package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.SetmealSupplies;
import com.cn.dao.SetmealSuppliesDao;
import com.cn.service.SetmealSuppliesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 添加团购关系数据表
     *
     * @param setmealSuppliesList
     * @return
     */
    @Override
    public boolean saveBatch(List<SetmealSupplies> setmealSuppliesList) {

        for (SetmealSupplies setmealSupplies : setmealSuppliesList) {
            setmealSuppliesDao.insert(setmealSupplies);
        }
        return true;
    }

    @Override
    public boolean deleteBySetmealIds(Long[] ids) {
        // 构造条件
        LambdaQueryWrapper<SetmealSupplies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealSupplies::getSetmealId, ids);
        setmealSuppliesDao.delete(queryWrapper);
        return true;
    }

    /**
     * 查询数据
     *
     * @param queryWrapper
     * @return
     */
    @Override
    public List<SetmealSupplies> selectList(LambdaQueryWrapper<SetmealSupplies> queryWrapper) {
        List<SetmealSupplies> setmealSupplies = setmealSuppliesDao.selectList(queryWrapper);
        return setmealSupplies;
    }

    /**
     * 根据条件删除关系表数据
     *
     * @param queryWrapper
     * @return
     */
    @Override
    public boolean delete(LambdaQueryWrapper<SetmealSupplies> queryWrapper) {
        return setmealSuppliesDao.delete(queryWrapper) > 0;
    }
}
