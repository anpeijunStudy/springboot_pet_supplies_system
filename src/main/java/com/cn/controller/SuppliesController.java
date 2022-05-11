package com.cn.controller;

import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Supplies;
import com.cn.service.RemarkService;
import com.cn.service.SuppliesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private RemarkService remarkService;


    /**
     * 新增用品
     *
     * @param suppliesDto 用品数据
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SuppliesDto suppliesDto) {
        log.info("新增用品信息{}" + suppliesDto.toString());
        boolean saveWithRemark = suppliesService.saveWithRemark(suppliesDto);
        if (saveWithRemark) {
            return new Result(Code.POST_OK, null, "添加成功");
        } else {
            return new Result(Code.POST_OK, null, "添加失败");
        }
    }
}

