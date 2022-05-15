package com.cn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Orders;
import com.cn.entity.OrdersFront;

import java.util.Date;

/**
 * 订单表(Orders)表服务接口
 *
 * @author makejava
 * @since 2022-05-14 20:54:55
 */
public interface OrdersService {


    /**
     * 查询分页数据
     * @param ordersFront
     * @return
     */
    Page page(OrdersFront ordersFront);
}
