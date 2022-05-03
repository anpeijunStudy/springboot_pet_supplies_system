package com.cn.controller;


import com.cn.domain.User;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:21
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public void login(){
        User byId = userService.getById(1);
        System.out.println(byId);
    }
}

