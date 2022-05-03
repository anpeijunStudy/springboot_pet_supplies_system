package com.cn.domain;


import lombok.Data;

/**
 * (User)表实体类
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:21
 */
@Data
public class User {
    //用户默认唯一ID
    private Integer id;
    //账号
    private String username;
    //密码
    private String password;

}

