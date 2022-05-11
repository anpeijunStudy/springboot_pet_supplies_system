package com.cn.dao;

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
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;


    @Test
    public void test01(){

    }
}
