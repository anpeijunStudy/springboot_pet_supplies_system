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
    private RemarkService remarkService;
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
        log.info("新增用品信息{}" + suppliesDto.toString());
        boolean saveWithRemark = suppliesService.saveWithRemark(suppliesDto);
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

        Page<Supplies> pageCondition = new Page(page, pageSize);
        Page<SuppliesDto> pageDtoCondition = new Page();
        // 查询条件
        LambdaQueryWrapper<Supplies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Supplies::getName, name);
        queryWrapper.orderByDesc(Supplies::getUpdateTime);

        Page selectPage = suppliesService.page(pageCondition, queryWrapper);

        // 对象拷贝-忽略records
        BeanUtils.copyProperties(pageCondition, pageDtoCondition, "records");


        List<Supplies> suppliesList = pageCondition.getRecords();
        System.out.println(suppliesList);
        List<SuppliesDto> suppliesDtoList = new ArrayList<>();

        // 根据ID查询名称（category）
        for (Supplies supplies : suppliesList) {
            SuppliesDto suppliesDto = new SuppliesDto();

            // 将普通属性全部拷贝
            BeanUtils.copyProperties(supplies, suppliesDto);

            Long categoryId = supplies.getCategoryId();// 得到categroy的ID
            Category selectByIDName = categoryService.selectByID(categoryId);
            suppliesDto.setCategoryName(selectByIDName.getName());
            suppliesDtoList.add(suppliesDto);
        }

        // 设置pageDtoCondition的records
        pageDtoCondition.setRecords(suppliesDtoList);

//        System.out.println(pageDtoCondition);
        return new Result(Code.GET_OK, pageDtoCondition, "查询成功");
    }


    /**
     * 根据ID查询用品信息和对应的备注信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result get(@PathVariable Long id) {
        SuppliesDto byIDCatchWithRemark = suppliesService.getByIDCatchWithRemark(id);
        if (byIDCatchWithRemark != null) {
            return new Result(Code.GET_OK, byIDCatchWithRemark, "查询成功");
        } else {
            return new Result(Code.GET_ERR, null, "查询失败");
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
        System.out.println(suppliesDto);
            log.info(suppliesDto.toString());
        boolean updateWithRemark = suppliesService.updateWithRemark(suppliesDto);
        if (updateWithRemark) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }
}

