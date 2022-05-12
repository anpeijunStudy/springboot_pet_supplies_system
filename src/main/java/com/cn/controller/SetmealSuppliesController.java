package com.cn.controller;

import com.cn.entity.SetmealSupplies;
import com.cn.service.SetmealSuppliesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 团购用品关系(SetmealSupplies)表控制层
 *
 * @author makejava
 * @since 2022-05-12 16:27:46
 */
@RestController
@RequestMapping("/setmealSupplies")
public class SetmealSuppliesController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealSuppliesService setmealSuppliesService;


}

