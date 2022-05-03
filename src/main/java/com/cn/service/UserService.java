package com.cn.service;


import com.cn.domain.User;

/**
 * (User)表服务接口
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:21
 */
public interface UserService {
    /**
     * 根据ID获取book单条数据
     *
     * @param id
     * @return Book
     * {@code getById}
     */
    User getById(Integer id);
}

