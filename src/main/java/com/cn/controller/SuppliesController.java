package com.cn.controller;

import com.cn.entity.Supplies;
import com.cn.service.SuppliesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 宠物用品管理(Supplies)表控制层
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@RestController
@RequestMapping("/supplies")
public class SuppliesController {
    /**
     * 服务对象
     */
    @Resource
    private SuppliesService suppliesService;


}

