package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dao.SetmealDao;
import com.cn.dao.SuppliesDao;
import com.cn.entity.Category;
import com.cn.dao.CategoryDao;
import com.cn.entity.Setmeal;
import com.cn.entity.Supplies;
import com.cn.service.CategoryService;
import com.cn.service.SetmealService;
import com.cn.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用品及团购分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-05-10 14:41:55
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;  // 用品和团购
    @Autowired
    private SetmealService setmealService;  // 具体团购
    @Autowired
    private SuppliesService suppliesService; // 具体用品

    @Override
    public boolean save(Category category) {
        return categoryDao.insert(category) > 0;
    }

    @Override
    public Page page(Page categoryPage, LambdaQueryWrapper<Category> queryWrapper) {
        return categoryDao.selectPage(categoryPage, queryWrapper);
    }

    @Override
    public boolean delete(Long id) {
        return categoryDao.deleteById(id) > 0;
    }

    @Override
    public boolean remove(Long id) {

        LambdaQueryWrapper<Supplies> suppliesLQW = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLQW = new LambdaQueryWrapper<>();
        // 添加分类条件-id
        suppliesLQW.eq(Supplies::getCategoryId, id);
        setmealLQW.eq(Setmeal::getCategoryId, id);
        // 1-1-查询当前宠物用品下是否存在具体宠物用品
        Integer suppliesCount = suppliesService.count(suppliesLQW);
        // System.out.println(suppliesCount);
        // 1-2-查询当前团购是否关联了具体的套餐
        Integer setmealCount = setmealService.count(setmealLQW);
        // 2-删除宠物用品或者团购
        // System.out.println(setmealCount);
        if (setmealCount == 0 && suppliesCount == 0) {
            // 删除
            this.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.updateById(category) > 0;
    }
}
