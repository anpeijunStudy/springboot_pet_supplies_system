package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Category;
import com.cn.dao.CategoryDao;
import com.cn.service.CategoryService;
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
    private CategoryDao categoryDao;

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
}
