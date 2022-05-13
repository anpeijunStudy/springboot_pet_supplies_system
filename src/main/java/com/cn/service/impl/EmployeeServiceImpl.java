package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Employee;
import com.cn.dao.EmployeeDao;
import com.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    /**
     * 查询账号密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee select(String username, String password) {
        Employee employee = employeeDao.selectAllByPasswordAndUsernameEmployee(username, password);
        return employee;
    }


    /**
     * 添加员工
     *
     * @param employee 新增员工信息
     */
    @Override
    public boolean save(Employee employee) {
        return employeeDao.insert(employee) > 0;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public Page page(int page, int pageSize, String name) {

        // 判断name是否有值
        Page pageInfo = new Page(page, pageSize);
        // 添加过滤条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(name != null, Employee::getName, name);
        // 查询
        Page selectPage = employeeDao.selectMapsPage(pageInfo, queryWrapper);
        return selectPage;
    }

    /**
     * 根据ID修改
     *
     * @param employee 修改员工信息
     * @return
     */
    @Override
    public boolean updateByID(Employee employee) {
        return employeeDao.updateById(employee) > 0;
    }

    /**
     * 根据ID查询员工
     *
     * @param id 员工ID
     * @return
     */
    @Override
    public Employee getByID(Integer id) {
        return employeeDao.selectById(id);
    }
}
