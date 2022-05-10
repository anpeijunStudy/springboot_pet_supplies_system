package com.cn.controller;

import com.cn.entity.Setmeal;
import com.cn.service.SetmealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 团购(Setmeal)表控制层
 *
 * @author makejava
 * @since 2022-05-10 20:12:39
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealService setmealService;


}

