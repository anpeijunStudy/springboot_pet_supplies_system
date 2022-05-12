package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Setmeal;
import com.cn.entity.SetmealSupplies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团购用品关系(SetmealSupplies)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-12 16:27:46
 */
@Mapper
public interface SetmealSuppliesDao extends BaseMapper<SetmealSupplies> {


}

