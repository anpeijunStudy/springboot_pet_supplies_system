package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.exception.CustomException;
import com.cn.dto.SetmealDto;
import com.cn.entity.Category;
import com.cn.entity.Setmeal;
import com.cn.dao.SetmealDao;
import com.cn.entity.SetmealSupplies;
import com.cn.service.CategoryService;
import com.cn.service.SetmealService;
import com.cn.service.SetmealSuppliesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 团购(Setmeal)表服务实现类
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@Service("setmealService")
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealDao setmealDao;
    @Resource
    private SetmealSuppliesService setmealSuppliesService;
    @Resource
    private CategoryService categoryService;

    /**
     * 查询CategroyId的Count
     *
     * @param setmealLQW
     * @return
     */
    @Override
    public Integer count(LambdaQueryWrapper<Setmeal> setmealLQW) {
        return setmealDao.selectCount(setmealLQW);
    }

    /**
     * 新增团购
     *
     * @param stemealDto
     * @return
     */
    @Override
    @Transactional
    public boolean saveWithSupplies(SetmealDto stemealDto) {

        // 保存基本团购信息
        Integer insert = setmealDao.insert(stemealDto);
        // 保存团购和宠物用品的关联信息
        List<SetmealSupplies> setmealSuppliesList = stemealDto.getSetmealDishes();
        for (SetmealSupplies setmealSupplies : setmealSuppliesList) {
            setmealSupplies.setSetmealId(stemealDto.getId());
        }
        // 添加
        boolean saveBatch = setmealSuppliesService.saveBatch(setmealSuppliesList);
        if (insert > 0 && saveBatch) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page page(Integer page, Integer pageSize, String name) {
        // 查询分页数据-需要返回setmealDto(包含Setmeal和Cateproy)
        Page<SetmealDto> setmealDtoPage = new Page<SetmealDto>();
        // 查询pet_setmeal
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        // 查询条件
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        Page<Setmeal> selectPage = setmealDao.selectPage(setmealPage, queryWrapper);
        // 对象拷贝
        BeanUtils.copyProperties(selectPage, setmealDtoPage, "records");

        // 得到分页数据
        List<Setmeal> records = selectPage.getRecords();
        List<SetmealDto> setmealDtoList = new ArrayList<>();
        // 将团购所属名称给具体团购
        for (Setmeal record : records) {
            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(record, setmealDto);

            Long categoryId = record.getCategoryId();
            Category category = categoryService.selectByID(categoryId);

            setmealDto.setCategoryName(category.getName());

            setmealDtoList.add(setmealDto);
        }
        setmealDtoPage.setRecords(setmealDtoList);
        return setmealDtoPage;
    }

    @Override
    @Transactional
    public boolean deleteWithSupplies(Long[] ids) {
        // 首先判断是不是停售状态
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);// 是否是起售的状态
        Integer integer = setmealDao.selectCount(queryWrapper);

        // 如果不能删除，抛出一个异常
        if (integer > 0) {
            throw new CustomException("套餐正在售卖中-不可删除");
        }
        // 如果可以删除，先删除关系表数据
        LambdaQueryWrapper<Setmeal> deleteQueryWrapper = new LambdaQueryWrapper<>();
        deleteQueryWrapper.in(Setmeal::getId, ids);
        setmealDao.delete(deleteQueryWrapper);
        // 删除套餐表数据
        setmealSuppliesService.deleteBySetmealIds(ids);

        return true;
    }

    /**
     * 修改售卖状态
     *
     * @param ids
     * @param state
     * @return
     */
    @Override
    public boolean updateStatus(Integer[] ids, int state) {
        // 条件构造
        for (Integer id : ids) {
            setmealDao.updateByIdStatus(id, state);
        }
        // 修改
        return true;
    }

    /**
     * 回显数据
     *
     * @param id
     * @return
     */
    @Override
    public SetmealDto selectById(Long id) {

        SetmealDto setmealDto = new SetmealDto();
        // 查询pet_setmeal
        Setmeal setmeal = setmealDao.selectById(id);
        BeanUtils.copyProperties(setmeal, setmealDto);

        // 查询pet_setmeal_supplies
        LambdaQueryWrapper<SetmealSupplies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealSupplies::getSetmealId, id);
        List<SetmealSupplies> setmealSupplies = setmealSuppliesService.selectList(queryWrapper);

        setmealDto.setSetmealDishes(setmealSupplies);

        return setmealDto;
    }

    /**
     * 修改数据
     *
     * @param setmealDto
     * @return
     */
    @Override
    @Transactional
    public Boolean updateWithSetmealSupplie(SetmealDto setmealDto) {
        // 需要修改pet_setmeal和pet_setmeal_supplies

        // 修改pet_setmeal
        Integer updateByIdFlag = setmealDao.updateById(setmealDto);

        // 修改pet_setmeal_supplies表
        // 先删除-构造条件
        LambdaQueryWrapper<SetmealSupplies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealSupplies::getSetmealId, setmealDto.getId());
        boolean deleteFlag = setmealSuppliesService.delete(queryWrapper);

        // 添加数据
        List<SetmealSupplies> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealSupplies setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealDto.getId());
        }
        boolean saveBatch = setmealSuppliesService.saveBatch(setmealDishes);
        if (deleteFlag && updateByIdFlag > 0 && saveBatch) {
            return true;
        } else {
            return false;
        }

    }
}
