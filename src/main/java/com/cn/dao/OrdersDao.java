package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表(Orders)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-14 20:54:54
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {


}

