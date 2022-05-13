package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Category;

import java.util.List;

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
     * @param page
     * @param pageSize
     * @return
     */
    Page page(Integer page, Integer pageSize);

    /**
     * 删除用品或者团购
     *
     * @param id 相对应的用品或者团购
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据ID去移除相应的物品（观察所属关系）
     *
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 修改数据-本质上是根据ID修改
     *
     * @param category 修改数据
     * @return
     */
    boolean update(Category category);


    /**
     * 根据type来查询对应的用品或者团购信息
     * @param category 封装type和排序
     * @return
     */
    List<Category> list(Category category);

    /**
     * 根据ID查询名称
     *
     * 用品管理和团购管理的添加都需要回显数据展示
     * @param categoryId
     * @return
     */
    Category selectByID(Long categoryId);
}
