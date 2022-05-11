package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用品及团购分类(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-10 14:41:54
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

    /**
     *  根据id查询得出用品名字
     * @param categoryId
     * @return
     */
    String selectByIDReacheName(Long categoryId);
}

