package com.cn.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息(User)实体类
 *
 * @author makejava
 * @since 2022-05-14 20:54:56
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -16089346513584164L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;

}

