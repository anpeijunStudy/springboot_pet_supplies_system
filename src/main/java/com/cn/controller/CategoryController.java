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
        log.info(category.getId() + "---" + category.getName() + "开始添加");
        // mybatis-plus自动填充字段
        // 添加数据
        boolean save = categoryService.save(category);

        // 判断添加是否成功
        if (save) {
            return Result.postOK();
        } else {
            return Result.postOK();
        }

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
        log.info("用品或者团购数据表查询");
        // 查询
        Page selectPage = categoryService.page(page, pageSize);
        // 判断
        if (selectPage != null) {
            return new Result(Code.GET_OK, selectPage, "查询成功");
        } else {
            return Result.getErr();
        }
    }

    /**
     * 根据ID删除 用品或者团购
     *
     * @param id 用品或者团购id
     * @return
     */
    @DeleteMapping
    public Result deleteId(Long id) {
        log.info("删除用户的id{}" + id);

        // 删除
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
        // 修改
        boolean update = categoryService.update(category);

        // 判断
        if (update) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    /**
     * 根据type来查询用品或者团购种类
     * <p>
     * 用品管理和团购管理的添加都需要回显数据
     *
     * @param category 封装type
     * @return
     */
    @GetMapping("/list")
    public Result list(Category category) {

        // 查询
        List<Category> categoryList = categoryService.list(category);

        // 判断
        if (categoryList != null) {
            return new Result(Code.GET_OK, categoryList, "查询成功");
        } else {
            return Result.getErr();
        }
    }
}

