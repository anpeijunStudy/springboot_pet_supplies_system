package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Orders;
import com.cn.dao.OrdersDao;
import com.cn.entity.OrdersFront;
import com.cn.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2022-05-14 20:54:56
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;

    /**
     * 查询分页数据
     *
     * @param ordersFront
     * @return
     */
    @Override
    public Page page(OrdersFront ordersFront) {
        // 查询分页数据
        Page<Orders> page = new Page(ordersFront.getPage(), ordersFront.getPageSize());
        // 构建条件
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ordersFront.getNumber() != null, Orders::getNumber, ordersFront.getNumber());
        if (ordersFront.getBeginTime() != null && ordersFront.getEndTime() != null) {
            queryWrapper.between(Orders::getCheckoutTime, ordersFront.getBeginTime(), ordersFront.getEndTime());
        }

        // 查询
        Page<Orders> ordersPage = ordersDao.selectPage(page, queryWrapper);
        return ordersPage;
    }
}
