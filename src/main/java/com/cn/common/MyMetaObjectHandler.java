package com.cn.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cn.util.BaseContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 安珮军
 * @version 1.0
 * @Description: 元数据对象处理器
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作-自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]");
        log.info(metaObject.toString());


        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContextUtils.getCurrentId());
        metaObject.setValue("updateUser", BaseContextUtils.getCurrentId());
        // System.out.println(BaseContextUtils.getCurrentId());
    }

    /**
     * 更新操作-自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        /*
        获得线程Id
        long id = Thread.currentThread().getId();
        log.info("线程ID{}" + id);*/

        log.info("公共字段自动填充[update]");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContextUtils.getCurrentId());
    }
}
