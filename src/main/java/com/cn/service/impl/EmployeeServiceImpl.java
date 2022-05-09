package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.entity.Employee;
import com.cn.dao.EmployeeDao;
import com.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author makejava
 * @since 2022-05-04 16:19:43
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public Employee select(String username, String password) {
        Employee employee = employeeDao.selectAllByPasswordAndUsernameEmployee(username, password);
        return employee;
    }
}
