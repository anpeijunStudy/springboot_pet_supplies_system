package com.cn.service.impl;

import com.cn.entity.Supplies;
import com.cn.dao.SuppliesDao;
import com.cn.service.SuppliesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
