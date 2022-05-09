package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Employee;

import java.util.List;

/**
 * 员工信息(Employee)表服务接口
 *
 * @author makejava
 * @since 2022-05-04 16:19:43
 */
public interface EmployeeService {
    /**
     * 查询账号密码
     *
     * @param username
     * @param password
     * @return
     */
    Employee select(String username, String password);

    /**
     * 添加员工
     *
     * @param employee 新增员工信息
     */
    boolean save(Employee employee);

    Page page(Page pageInfo, LambdaQueryWrapper<Employee> queryWrapper);
}
