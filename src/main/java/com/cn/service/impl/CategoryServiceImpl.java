package com.cn.service.impl;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dao.SetmealDao;
import com.cn.dao.SuppliesDao;
import com.cn.entity.Category;
import com.cn.dao.CategoryDao;
import com.cn.entity.Setmeal;
import com.cn.entity.Supplies;
import com.cn.exception.CustomException;
import com.cn.service.CategoryService;
import com.cn.service.SetmealService;
import com.cn.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 添加用品
     *
     * @param category
     * @return
     */
    @Override
    public boolean save(Category category) {
        return categoryDao.insert(category) > 0;
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page page(Integer page, Integer pageSize) {
        // 分页查询
        Page categoryPage = new Page(page, pageSize);
        // 条件构造
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getType).orderByAsc(Category::getSort);

        return categoryDao.selectPage(categoryPage, queryWrapper);
    }

    /**
     * 删除用品或者团购
     *
     * @param id 相对应的用品或者团购
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return categoryDao.deleteById(id) > 0;
    }

    /**
     * 根据ID去移除相应的物品（观察所属关系）
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean remove(Long id) {

        // 删除的时候需要判断是否没有所属关系

        // 用品表条件构造
        LambdaQueryWrapper<Supplies> suppliesLQW = new LambdaQueryWrapper<>();
        suppliesLQW.eq(Supplies::getCategoryId, id);

        // 团购表条件构造
        LambdaQueryWrapper<Setmeal> setmealLQW = new LambdaQueryWrapper<>();
        setmealLQW.eq(Setmeal::getCategoryId, id);


        // 1-1-查询当前宠物用品下是否存在具体宠物用品
        Integer suppliesCount = suppliesService.count(suppliesLQW);
        if (suppliesCount > 0) {
            throw new CustomException("此用品种类下存在用品");
        }
        // 1-2-查询当前团购是否关联了具体的套餐
        Integer setmealCount = setmealService.count(setmealLQW);
        if (setmealCount > 0) {
            throw new CustomException("此团购种类下存在套餐");
        }

        // 2-删除宠物用品或者团购
        if (setmealCount == 0 && suppliesCount == 0) {
            // 删除
            this.delete(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改数据-本质上是根据ID修改
     *
     * @param category 修改数据
     * @return
     */
    @Override
    public boolean update(Category category) {
        return categoryDao.updateById(category) > 0;
    }

    /**
     * 根据type来查询对应的用品或者团购信息
     *
     * @param category 封装type和排序
     * @return
     */
    @Override
    public List<Category> list(Category category) {
        // 条件构造
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        return categoryDao.selectList(queryWrapper);
    }

    /**
     * 根据ID查询名称
     * <p>
     * 用品管理和团购管理的添加都需要回显数据展示
     *
     * @param categoryId
     * @return
     */
    @Override
//    @Cached(area = "pet",name="category",key = "#categoryId")
    public Category selectByID(Long categoryId) {
        return categoryDao.selectById(categoryId);
    }
}
