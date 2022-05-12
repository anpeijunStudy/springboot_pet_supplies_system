package com.cn.controller;

import com.cn.common.Result;
import com.cn.dto.StemealDto;
import com.cn.entity.Setmeal;
import com.cn.service.SetmealService;
import com.cn.service.SetmealSuppliesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public Result save(@RequestBody StemealDto stemealDto) {
        log.info("套餐信息{}" + stemealDto);
        // 添加信息
        setmealService.saveWithSupplies(stemealDto);
        return Result.postErr();
    }


}

