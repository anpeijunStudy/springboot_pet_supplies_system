package com.cn.controller;

import com.cn.code.Code;
import com.cn.common.Result;
import com.cn.entity.Employee;
import com.cn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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


    @GetMapping
    public Result login() {
        return new Result(Code.GET_ERR, null, "账号禁用");
    }

    /**
     * 员工登录
     * <p>
     * //     * @param request  存取Session
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
            request.getSession().setAttribute("employee", employee.getId());
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
}

