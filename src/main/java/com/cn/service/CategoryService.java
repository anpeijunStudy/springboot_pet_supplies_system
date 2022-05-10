package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Category;

/**
 * 用品及团购分类(Category)表服务接口
 *
 * @author makejava
 * @since 2022-05-10 14:41:55
 */
public interface CategoryService {


    /**
     * 添加用品
     *
     * @param category
     * @return
     */
    boolean save(Category category);

    /**
     * 查询分页数据
     *
     * @param categoryPage 分页值
     * @param queryWrapper 条件值
     * @return
     */
    Page page(Page categoryPage, LambdaQueryWrapper<Category> queryWrapper);

    /**
     * 删除用品或者团购
     *
     * @param id 相对应的用品或者团购
     * @return
     */
    boolean delete(Long id);
}
