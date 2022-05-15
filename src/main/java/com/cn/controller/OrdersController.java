package com.cn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.entity.Orders;
import com.cn.entity.OrdersFront;
import com.cn.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Date;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2022-05-14 20:54:54
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    @GetMapping("/page")
    public Result page(OrdersFront ordersFront) {
        log.info("订单数据表开始查询");
        System.out.println(ordersFront.toString());

        Page selectPage = ordersService.page(ordersFront);

        if (selectPage != null) {
            return new Result(Code.GET_OK, selectPage, "查询成功");
        } else {
            return Result.getErr();
        }
    }
}

