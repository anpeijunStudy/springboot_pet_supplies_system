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
     * 验证账号密码
     *
     * @param request  存取Session
     * @param employee 获取username和password
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Employee employee) {

        // 查询数据库账号密码
        Employee selectEmployee = employeeService.select(employee.getUsername(), employee.getPassword());
        log.info("员工" + selectEmployee.getName() + "尝试登录");

        // 判断是否登录成功
        if (selectEmployee == null) {
            return Result.postErr();
        } else if (selectEmployee.getStatus() == 0) {
            // 表示账号已经禁用
            return new Result(Code.POST_ERR, null, "账号禁用");
        } else {
            // 登录成功
            // 将ID存入Session中
            request.getSession().setAttribute("employee", selectEmployee.getId());

            // 密码设置为空
            selectEmployee.setPassword(null);
            log.info("员工" + selectEmployee.getName() + "登录成功");
            // 遇见的问题-第一次返回了一个selectEmployee.getUsername导致后面的验证是否为admin没有通过
            return new Result(Code.POST_OK, selectEmployee, "登录成功");
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

/*
         交给mybatis-plus的公共字段自动填充
        // 创建时间
        employee.setCreateTime(LocalDateTime.now());
        // 更新时间
        employee.setUpdateTime(LocalDateTime.now());
        // 创建人
        Long createUserId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(createUserId);
        // 更新人
        employee.setUpdateUser(createUserId);*/

/*      // 判断是否添加成功-如果数据库有相同的username抛出异常
        try {
            // 如果成功的话按照向下
            boolean save = employeeService.save(employee);
            return new Result(Code.POST_OK, null, "添加成功");
        } catch (Exception e) {
            log.info("异常信息：" + e);
        }finally {
            // 最终会执行
            return new Result(Code.POST_ERR, null, "添加失败");

        }*/
        // 添加
        // 异常会由异常捕获器统一去处理
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

        log.info("员工信息表查询");

        // 执行查询
        Page employeePage = employeeService.page(page, pageSize, name);

        return new Result(Code.GET_OK, employeePage, "查询成功");
    }

    /**
     * 根据id修改员工信息
     *
     * @param employee 传过来的员工信息
     * @return
     */
    @PutMapping
    public Result update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.getId()+"---"+employee.getName()+"修改信息");


/*
        // 公共字段自动填充
        // 先修改修改时间
        employee.setUpdateTime(LocalDateTime.now());
        Long employeeId = (Long) request.getSession().getAttribute("employee");
        // 修改人
        employee.setUpdateUser(employeeId);
*/
        // 再根据ID修改其他信息
        boolean update = employeeService.updateByID(employee);

        // 根据查询返回结果
        if (update) {
            return Result.updateOK();
        } else {
            return Result.updateErr();
        }
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id) {
        log.info("员工"+id+"信息返回");
        // 查询
        Employee employee = employeeService.getByID(id);
        // 密码空值
        employee.setPassword(null);

        return new Result(Code.GET_OK, employee, "查询成功");
    }
}

