package com.cn.service;

import com.cn.SpringbootPetSuppliesSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:
 */
@SpringBootTest(classes = SpringbootPetSuppliesSystemApplication.class)
public class CateGroyServiceImpl {
    @Autowired
    private CategoryService categoryService;

    /**
     * 测试是否可以查到数据count
     */
    @Test
    public void removeTest() {
        boolean remove = categoryService.remove((long) 3);
        System.out.println(remove);
    }
}
