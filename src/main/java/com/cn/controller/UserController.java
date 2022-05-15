package com.cn.controller;

import com.cn.entity.User;
import com.cn.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息(User)表控制层
 *
 * @author makejava
 * @since 2022-05-14 20:54:56
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


}

