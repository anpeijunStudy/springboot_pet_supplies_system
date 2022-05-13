package com.cn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Category;
import com.cn.entity.Supplies;
import com.cn.service.CategoryService;
import com.cn.service.RemarkService;
import com.cn.service.SuppliesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 宠物用品管理(Supplies)表控制层
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@Slf4j
@RestController
@RequestMapping("/supplies")
public class SuppliesController {
    /**
     * 服务对象
     */
    @Resource
    private SuppliesService suppliesService;
    @Resource
    private CategoryService categoryService;


    /**
     * 新增用品
     *
     * @param suppliesDto 用品数据
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SuppliesDto suppliesDto) {
        log.info("新增用品信息{}" + suppliesDto.getId() + "---" + suppliesDto.getName());

        // 添加
        boolean saveWithRemark = suppliesService.saveWithRemark(suppliesDto);

        // 判断
        if (saveWithRemark) {
            return new Result(Code.POST_OK, null, "添加成功");
        } else {
            return new Result(Code.POST_OK, null, "添加失败");
        }
    }

    /**
     * 宠物用品分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize, String name) {
        log.info("用品数据表信息展示{}");

        // 查询
        Page pageDtoData = suppliesService.pageDto(page, pageSize, name);

        // 判断
        if (pageDtoData != null) {
            return new Result(Code.GET_OK, pageDtoData, "查询成功");
        } else {
            return Result.getErr();
        }
    }


    /**
     * 根据ID查询用品信息和对应的备注信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result get(@PathVariable Long id) {
        log.info("查询" + id + "用品信息");

        // 查询
        SuppliesDto byIDCatchWithRemark = suppliesService.getByIDCatchWithRemark(id);

        // 判断
        if (byIDCatchWithRemark != null) {
            return new Result(Code.GET_OK, byIDCatchWithRemark, "查询成功");
        } else {
            return Result.getErr();
        }
    }


    /**
     * 修改用品信息
     *
     * @param suppliesDto 数据
     * @return
     */
    @PutMapping
    public Result update(@RequestBody SuppliesDto suppliesDto) {
        log.info(suppliesDto.toString());
        // 修改
        boolean updateWithRemark = suppliesService.updateWithRemark(suppliesDto);

        // 判断
        if (updateWithRemark) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    /**
     * 删除用品
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(Integer[] ids) {
        log.info("删除用品id{}" + ids.toString());

        // 删除
        boolean delete = suppliesService.delete(ids);

        // 判断
        if (delete) {
            return Result.deleteOK();
        } else {
            return Result.deleteErr();
        }
    }

    /**
     * 停售
     *
     * @param ids
     * @return
     */
    @PostMapping("/status/0")
    public Result status(Integer[] ids) {
        log.info(ids.toString() + "停售");

        // 修改
        boolean updateStatus = suppliesService.updateStatus(ids, 0);

        if (updateStatus) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    /**
     * 起售
     *
     * @param ids
     * @return
     */
    @PostMapping("/status/1")
    public Result stop(Integer[] ids) {
        log.info(ids.toString() + "起售");

        // 修改
        boolean updateStatus = suppliesService.updateStatus(ids, 1);

        if (updateStatus) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }


    /**
     * 根据条件来查询对应的宠物用品
     *
     * @param supplies 数据
     * @return
     */
    @GetMapping("/list")
    public Result list(Supplies supplies) {
        log.info("开始查询supplies数据表");

        // 查询supplies表中category_id对应的用品
        List<Supplies> list = suppliesService.list(supplies);

        if (list != null) {
            return new Result(Code.GET_OK, list, "查询成功");
        } else {
            return Result.getErr();
        }

    }

}

