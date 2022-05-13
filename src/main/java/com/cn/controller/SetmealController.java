package com.cn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.dto.SetmealDto;
import com.cn.service.SetmealService;
import com.cn.service.SetmealSuppliesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 团购(Setmeal)表控制层
 *
 * @author makejava
 * @since 2022-05-10 20:12:39
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealService setmealService;

    /**
     * 新增团购
     *
     * @param stemealDto
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SetmealDto stemealDto) {
        log.info("新增团购所属类" + stemealDto.getCategoryId());

        // 添加
        boolean saveWithSupplies = setmealService.saveWithSupplies(stemealDto);

        if (saveWithSupplies) {
            return Result.postOK();
        }
        return Result.postErr();
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize, String name) {
        log.info("开始查询团购管理分页数据");
        // 查询分页数据
        Page selectPage = setmealService.page(page, pageSize, name);

        if (selectPage != null) {
            return new Result(Code.GET_OK, selectPage, "查询成功");
        } else {
            return Result.getErr();
        }
    }

    /**
     * 删除团购套餐
     *
     * @param ids 团购ID
     * @return
     */
    @DeleteMapping
    public Result delete(Long[] ids) {
        log.info("删除团购表数据");
        // 删除
        boolean deleteWithSuppliesFlag = setmealService.deleteWithSupplies(ids);

        if (deleteWithSuppliesFlag == true) {
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
        boolean updateStatus = setmealService.updateStatus(ids, 0);

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
        boolean updateStatus = setmealService.updateStatus(ids, 1);

        if (updateStatus) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    /**
     * 根据ID回显数据
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Long id) {
        log.info(id + "团购套餐回显");

        // 查询
        SetmealDto setmealDto = setmealService.selectById(id);
        // 判断
        if (setmealDto != null) {
            return new Result(Code.GET_OK, setmealDto, "查询成功");
        } else {
            return Result.getErr();
        }
    }

    /**
     * 修改
     *
     * @param setmealDto
     * @return
     */
    @PutMapping
    public Result update(@RequestBody SetmealDto setmealDto) {
        log.info(setmealDto.getId() + "修改数据");
        System.out.println("修改数据" + setmealDto.toString());
        // 修改
        Boolean updateFlag = setmealService.updateWithSetmealSupplie(setmealDto);
        if (updateFlag) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }
}

