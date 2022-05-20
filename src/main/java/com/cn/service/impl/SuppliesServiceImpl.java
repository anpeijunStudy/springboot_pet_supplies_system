package com.cn.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Category;
import com.cn.entity.Remark;
import com.cn.entity.Supplies;
import com.cn.dao.SuppliesDao;
import com.cn.service.CategoryService;
import com.cn.service.RemarkService;
import com.cn.service.SuppliesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 宠物用品管理(Supplies)表服务实现类
 *
 * @author makejava
 * @since 2022-05-10 20:12:41
 */
@Service("suppliesService")
public class SuppliesServiceImpl implements SuppliesService {
    @Resource
    private SuppliesDao suppliesDao;
    // 操作宠物用品备注表
    @Resource
    private CategoryService categoryService;
    @Resource
    private RemarkService remarkService;


    /**
     * 统计CategoryID的count
     * 删除category需要统计
     *
     * @param suppliesLQW
     * @return
     */
    @Override
    public Integer count(LambdaQueryWrapper<Supplies> suppliesLQW) {
        return suppliesDao.selectCount(suppliesLQW);
    }

    /**
     * 新增菜品（同时操作两张表pet_supplies和 pet_supplies_reamrk）
     *
     * @param suppliesDto 数据
     * @return
     */
    @Override
    @Transactional // 开启事务注解
    public boolean saveWithRemark(SuppliesDto suppliesDto) {

        // 保存宠物用品的基本信息到pet_supplies表
        Integer suppliesInsert = suppliesDao.insert(suppliesDto);

        // 为每一个备注赋值用品ID
        Long id = suppliesDto.getId();
        List<Remark> remarks = suppliesDto.getFlavors();
        for (Remark remark : remarks) {
            remark.setPetSuppliesId(id);
        }

        // 保存备注-保存的是一个集合
        boolean save = remarkService.save(suppliesDto.getFlavors());

        if (suppliesInsert > 0 && save) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 根据用户ID查询用户表和其对应的备注信息
     *
     * @param id
     * @return
     */
    @Override
    @Cached(area = "pet", name = "supplies", key = "#id", expire = 100,cacheType = CacheType.REMOTE)
    public SuppliesDto getByIDCatchWithRemark(Long id) {

        // 查询用品信息
        Supplies supplies = suppliesDao.selectById(id);

        // 查询用品备注信息
        Long suppliesId = supplies.getId();

        // 条件构造
        LambdaQueryWrapper<Remark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(suppliesId != null, Remark::getPetSuppliesId, suppliesId);

        // 查询
        List<Remark> remarks = remarkService.selectByIdList(queryWrapper);

        // 使用对象拷贝
        SuppliesDto suppliesDto = new SuppliesDto();
        BeanUtils.copyProperties(supplies, suppliesDto);

        // 为备注集合赋值
        suppliesDto.setFlavors(remarks);

        return suppliesDto;
    }

    /**
     * 修改数据
     *
     * @param suppliesDto
     * @return
     */
    @Override
    @Transactional
    @CacheUpdate(area = "pet",name="supplies",key = "#suppliesDto.id",value = "#suppliesDto")
    public boolean updateWithRemark(SuppliesDto suppliesDto) {
        // 需要同时修改pet_supplies和pet_supplies_remark

        // 修改用品信息
        Integer updateById = suppliesDao.updateById(suppliesDto);

        // 修改用品备注信息-先删除再添加
        // 条件构造
        LambdaQueryWrapper<Remark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Remark::getPetSuppliesId, suppliesDto.getId());
        // 删除pet_supplies_remark
        Integer delete = remarkService.delete(queryWrapper);
        // 添加信息表
        List<Remark> flavors = suppliesDto.getFlavors();
        boolean save = remarkService.save(flavors);

        if (save && updateById > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 批量删除用品（需要同时删除信息表）
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public boolean delete(Integer[] ids) {

        for (Integer id : ids) {
            // 删除用品表
            suppliesDao.deleteById(id);
            // 根据用品ID删除用品备注表
            LambdaQueryWrapper<Remark> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(id != null, Remark::getPetSuppliesId, id);
            try {
                remarkService.delete(queryWrapper);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return true;
    }

    /***
     * 用品起售或者停售
     * @param ids
     * @return
     */
    @Override
    public boolean updateStatus(Integer[] ids, Integer state) {

        for (Integer id : ids) {
            suppliesDao.updateByIdChangeStatus(id, state);
        }
        return true;
    }

    /**
     * 根据查询条件返回List结果
     *
     * @param supplies
     * @return
     */
    @Override
    public List<Supplies> list(Supplies supplies) {
        // 构造条件
        LambdaQueryWrapper<Supplies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(supplies != null, Supplies::getCategoryId, supplies.getCategoryId());
        // 查询正在售卖的
        queryWrapper.eq(Supplies::getStatus, 1);
        queryWrapper.orderByAsc(Supplies::getSort).orderByDesc(Supplies::getUpdateTime);

        return suppliesDao.selectList(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return suppliesDto
     */
    @Override
    public Page pageDto(Integer page, Integer pageSize, String name) {

        // 条件
        Page<Supplies> suppliesPage = new Page<Supplies>(page, pageSize);
        Page<SuppliesDto> suppliesDtoPage = new Page<>();

        // 构造查询条件
        LambdaQueryWrapper<Supplies> suppliesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        suppliesLambdaQueryWrapper.like(name != null, Supplies::getName, name);

        // 查询用品表
        Page<Supplies> selectSuppliesPage = suppliesDao.selectPage(suppliesPage, suppliesLambdaQueryWrapper);

        // 数据拷贝
        BeanUtils.copyProperties(selectSuppliesPage, suppliesDtoPage, "records");
        // 根据查询到的分页值中的category_id去查询所属类别并赋值
        List<Supplies> suppliesList = selectSuppliesPage.getRecords();

        ArrayList<SuppliesDto> suppliesDtoList = new ArrayList<>();

        for (Supplies supplies : suppliesList) {

            // 用于添加suppliesDtoList
            SuppliesDto suppliesDto = new SuppliesDto();

            // 数据拷贝
            BeanUtils.copyProperties(supplies, suppliesDto);

            // 得到name
            Long categoryId = supplies.getCategoryId();
            Category category = categoryService.selectByID(categoryId);

            // 添加具体名字
            suppliesDto.setCategoryName(category.getName());

            // 构建page中records
            suppliesDtoList.add(suppliesDto);
        }
        // 赋值
        suppliesDtoPage.setRecords(suppliesDtoList);

        return suppliesDtoPage;
    }
}
