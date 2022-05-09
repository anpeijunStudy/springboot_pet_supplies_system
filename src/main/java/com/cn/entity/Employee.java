package com.cn.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 员工信息(Employee)实体类
 *
 * @author makejava
 * @since 2022-05-04 16:19:43
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = -95625662683344885L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
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
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 修改人
     */
    private Long updateUser;

}

