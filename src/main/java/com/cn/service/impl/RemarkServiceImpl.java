package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.Remark;
import com.cn.dao.RemarkDao;
import com.cn.service.RemarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宠物用品关系表(Remark)表服务实现类
 *
 * @author makejava
 * @since 2022-05-10 23:57:27
 */
@Service("remarkService")
public class RemarkServiceImpl implements RemarkService {
    @Resource
    private RemarkDao remarkDao;

    /**
     * 添加集合到备注表中pet_supplies_remark
     *
     * @param flavors 备注表信息
     * @return
     */
    @Override
    public boolean save(List<Remark> flavors) {
        for (Remark flavor : flavors) {
            remarkDao.insert(flavor);
        }
        return true;
    }

    @Override
    /**
     * 根据supplies给的ID查询备注信息
     *
     * @param queryWrapper 条件查询
     * @return
     */
    public List<Remark> selectByIdList(LambdaQueryWrapper queryWrapper) {
        return remarkDao.selectList(queryWrapper);
    }

    /**
     * 根据ID删除信息
     *
     * @param queryWrapper
     * @return
     */
    @Override
    public Integer delete(LambdaQueryWrapper queryWrapper) {
        return remarkDao.delete(queryWrapper);
    }
}
