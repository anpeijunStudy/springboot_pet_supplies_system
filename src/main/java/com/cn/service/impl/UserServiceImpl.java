package com.cn.service.impl;

import com.cn.dao.UserDao;
import com.cn.domain.User;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:22
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Integer id) {
        return userDao.selectById(id);
    }
}

