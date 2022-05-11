package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Remark;
import com.cn.entity.Supplies;
import com.cn.dao.SuppliesDao;
import com.cn.service.RemarkService;
import com.cn.service.SuppliesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private RemarkService remarkService;

    @Override
    public Integer count(LambdaQueryWrapper<Supplies> suppliesLQW) {
        return suppliesDao.selectCount(suppliesLQW);
    }

    @Override
    @Transactional // 开启事务注解
    public boolean saveWithRemark(SuppliesDto suppliesDto) {
        // 保存宠物用品的基本信息到pet_supplies表
        int suppliesInsert = suppliesDao.insert(suppliesDto);

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

    @Override
    public Page page(Page page, LambdaQueryWrapper<Supplies> queryWrapper) {
        return suppliesDao.selectPage(page, queryWrapper);
    }

    @Override
    public SuppliesDto getByIDCatchWithRemark(Long id) {

        // 查询用品信息
        Supplies supplies = suppliesDao.selectById(id);
        // 查询用品备注信息
        Long suppliesId = supplies.getId();
        // 条件查询
        LambdaQueryWrapper<Remark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(suppliesId != null, Remark::getPetSuppliesId, suppliesId);
        List<Remark> remarks = remarkService.selectByIdList(queryWrapper);

        // 使用对象拷贝
        SuppliesDto suppliesDto = new SuppliesDto();
        BeanUtils.copyProperties(supplies, suppliesDto);
        suppliesDto.setFlavors(remarks);

        return suppliesDto;
    }

    @Override
    @Transactional
    public boolean updateWithRemark(SuppliesDto suppliesDto) {
        // 修改用品信息
//        QueryWrapper<Supplies> suppliesQueryWrapper = new QueryWrapper<>();
//        suppliesQueryWrapper.eq(suppliesDto!=null,SuppliesDto::getId,suppliesDto.getId());
        System.out.println(suppliesDto);
        int updateById = suppliesDao.updateById(suppliesDto);
        // 修改用品备注信息-先删除再添加
        LambdaQueryWrapper<Remark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(suppliesDto.getId() != null, Remark::getPetSuppliesId, suppliesDto.getId());
        boolean delete = remarkService.delete(queryWrapper);
        List<Remark> flavors = suppliesDto.getFlavors();
        remarkService.save(flavors);

        return true;
    }
}
