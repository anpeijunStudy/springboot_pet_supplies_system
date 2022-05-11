package com.cn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.entity.Category;
import com.cn.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用品及团购分类(Category)表控制层
 *
 * @author makejava
 * @since 2022-05-10 14:41:54
 */
@Slf4j
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param category 用品及团购数据
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Category category) {
        // 会由mybatisplus去填充字段
        boolean save = categoryService.save(category);
        log.info("添加的用品及团购数据{}" + category);
        return new Result(Code.POST_OK, null, "添加成功");
    }

    /**
     * 分页查询数据
     *
     * @param page     页码
     * @param pageSize 页数
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize) {
        // 分页查询
        Page categoryPage = new Page(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);

        // 查询
        Page selectPage = categoryService.page(categoryPage, queryWrapper);
        return new Result(Code.GET_OK, selectPage, "查询成功");
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result deleteId(Long id) {
        log.info("删除用户的id{}" + id);
        // 执行删除的方法
//        boolean delete = categoryService.delete(id);
        boolean remove = categoryService.remove(id);
        // 判读影响行数
        if (remove) {
            return Result.deleteOK();
        } else {
            return Result.deleteErr();
        }
    }

    /**
     * 修改用品或团购信息-根据ID
     *
     * @param category 数据
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Category category) {
        boolean update = categoryService.update(category);
        if (update) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    /**
     * 根据type来查询用品或者团购种类
     *
     * @param category 封装type
     * @return
     */
    @GetMapping("/list")
    public Result list(Category category) {
        // 条件查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        // 查询
        List<Category> list = categoryService.list(queryWrapper);
        return new Result(Code.GET_OK, list, "查询成功");
    }
}

