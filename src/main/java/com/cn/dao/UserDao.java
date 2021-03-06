package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-14 20:54:56
 */
@Mapper
public interface UserDao extends BaseMapper<User> {


}

