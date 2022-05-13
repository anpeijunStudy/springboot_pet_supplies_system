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
    @Resource
    private SetmealSuppliesService setmealSuppliesService;


    /**
     * 新增团购
     *
     * @param stemealDto
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SetmealDto stemealDto) {
        log.info("套餐信息{}" + stemealDto);
        System.out.println(stemealDto.getId() + "用品ID：" + stemealDto.getCategoryId());
        // 添加信息
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
        boolean deleteWithSuppliesFlag = setmealService.deleteWithSupplies(ids);
        if (deleteWithSuppliesFlag==true) {
            return Result.deleteOK();
        } else {
            return Result.deleteErr();
        }
    }
}

