package com.cn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.entity.Employee;
import com.cn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工信息(Employee)表控制层
 *
 * @author makejava
 * @since 2022-05-04 16:19:41
 */
@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * <p>
     * * @param request  存取Session
     *
     * @param employee 获取username和password
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Employee employee) {

        // 查询数据库
        Employee selectEmployee = employeeService.select(employee.getUsername(), employee.getPassword());

        if (selectEmployee == null) {
            return new Result(Code.POST_ERR, null, "登录失败");
        } else if (selectEmployee.getStatus() == 0) {// 表示账号已经禁用
            return new Result(Code.POST_ERR, null, "账号禁用");
        } else {// 登录成功
            // 将ID存入Session中
            request.getSession().setAttribute("employee", selectEmployee.getId());
            System.out.println(request.getSession().getAttribute("employee"));
            System.out.println(selectEmployee);
            return new Result(Code.POST_OK, selectEmployee.getUsername(), "登录成功");
        }
    }

    /**
     * 员工退出
     *
     * @param request 存放session
     * @return
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {

        // 清除session中现在登录的员工ID
        request.getSession().removeAttribute("employee");
        return new Result(Code.POST_OK, null, "退出成功");
    }

    /**
     * 新增员工
     *
     * @param employee 新员工信息
     * @return
     */
    @PostMapping
    public Result save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工-员工信息：{}" + employee);
        // 设置初始密码
        employee.setPassword("123456");
        // 创建时间
        employee.setCreateTime(LocalDateTime.now());
        // 更新时间
        employee.setUpdateTime(LocalDateTime.now());
        // 创建人
        Long createUserId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(createUserId);
        // 更新人
        employee.setUpdateUser(createUserId);

        // 添加
//        try {
//            // 如果成功的话按照向下
//            boolean save = employeeService.save(employee);
//            return new Result(Code.POST_OK, null, "添加成功");
//        } catch (Exception e) {
//            log.info("异常信息：" + e);
//        }finally {
//            // 最终会执行
//            return new Result(Code.POST_ERR, null, "添加失败");
//
//        }
        boolean save = employeeService.save(employee);
        return new Result(Code.POST_OK, null, "添加成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize, String name) {
        log.info("page={}" + page + "---pageSize{}" + pageSize + "---name{}" + name);
        // 判断name是否有值

        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        // 添加过滤条件
        queryWrapper.like(name != null, Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getCreateTime);
//        queryWrapper.like(StringUtils.isEmpty(name), Employee::getName, name).orderByDesc(Employee::getUpdateTime);
        // 执行查询
        Page employeePage = employeeService.page(pageInfo, queryWrapper);
        System.out.println(employeePage);

        return new Result(Code.GET_OK, employeePage, "查询成功");
    }
}

