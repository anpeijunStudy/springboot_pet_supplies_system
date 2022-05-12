package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Supplies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 宠物用品管理(Supplies)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-10 20:12:40
 */
@Mapper
public interface SuppliesDao extends BaseMapper<Supplies> {

    /**
     * 根据id去修改售卖状态
     *
     * @param id
     * @param state
     * @return
     */
    boolean updateByIdChangeStatus(@Param("id") Integer id, @Param("state") Integer state);
}

