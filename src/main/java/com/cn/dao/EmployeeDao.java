package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工信息(Employee)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-04 16:19:41
 */
@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {

    /**
     * 查询账号密码*/
    Employee selectAllByPasswordAndUsernameEmployee(@Param("username") String username,@Param("password")String password);
}

