package com.cn.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:21
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}

