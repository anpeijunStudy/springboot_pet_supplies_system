package com.cn.dao;

import com.cn.SpringbootPetSuppliesSystemApplication;
import com.cn.entity.Remark;
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
public class RemarkDaoTest {

    @Autowired
    private RemarkDao remarkDao;

    @Test
    public void basicTest() {
        List<Remark> remarks = remarkDao.selectList(null);
        for (Remark remark : remarks) {
            System.out.println(remark);
        }
    }
}
