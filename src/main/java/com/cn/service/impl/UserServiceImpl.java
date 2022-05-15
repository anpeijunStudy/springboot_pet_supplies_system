package com.cn.service.impl;

import com.cn.entity.User;
import com.cn.dao.UserDao;
import com.cn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息(User)表服务实现类
 *
 * @author makejava
 * @since 2022-05-14 20:54:56
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
}
