package com.cn.service;

import com.cn.SpringbootPetSuppliesSystemApplication;
import com.cn.dao.OrdersDao;
import com.cn.entity.Orders;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:
 */
@SpringBootTest(classes = SpringbootPetSuppliesSystemApplication.class)
public class OrderServiceTest {

    @Autowired
    private OrdersDao ordersDao;

    @Test
    public void dataTest() {
        List<Orders> orders = ordersDao.selectList(null);
        for (Orders order : orders) {
            System.out.println(order);
        }
    }
}
