package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Remark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 宠物用品关系表(Remark)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-10 23:57:26
 */
@Mapper
public interface RemarkDao extends BaseMapper<Remark> {


}

