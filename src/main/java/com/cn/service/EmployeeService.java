package com.cn.service;

import com.cn.entity.Employee;

/**
 * 员工信息(Employee)表服务接口
 *
 * @author makejava
 * @since 2022-05-04 16:19:43
 */
public interface EmployeeService {
    /**
     * 查询账号密码
     * @param username
     * @param password
     * @return
     */
    Employee select(String username,String password);
}
