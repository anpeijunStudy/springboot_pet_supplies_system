package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.dto.SuppliesDto;
import com.cn.entity.Remark;
import com.cn.entity.Supplies;
import com.cn.dao.SuppliesDao;
import com.cn.service.RemarkService;
import com.cn.service.SuppliesService;
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
}
