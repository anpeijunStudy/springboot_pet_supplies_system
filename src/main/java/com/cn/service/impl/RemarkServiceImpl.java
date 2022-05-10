package com.cn.service.impl;

import com.cn.entity.Remark;
import com.cn.dao.RemarkDao;
import com.cn.service.RemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
