package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团购(Setmeal)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-10 20:12:39
 */
@Mapper
public interface SetmealDao extends BaseMapper<Setmeal> {

    /**
     * 修改状态
     * @param id
     * @param state
     */
    void updateByIdStatus(@Param("id") Integer id, @Param("state") Integer state);
}

