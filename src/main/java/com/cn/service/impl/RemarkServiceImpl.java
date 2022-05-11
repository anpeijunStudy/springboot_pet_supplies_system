package com.cn.service.impl;

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

    @Override
    @Transactional
    public boolean save(List<Remark> flavors) {
        for (Remark flavor : flavors) {
            remarkDao.insert(flavor);
        }
        return true;
    }
}
