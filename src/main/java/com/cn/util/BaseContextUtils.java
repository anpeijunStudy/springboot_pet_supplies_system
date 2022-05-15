package com.cn.util;

/**
 * @author 安珮军
 * @version 1.0
 * @Description: 基于ThreadLocal封装工具类，用于保存和获取当前登录用户的ID
 * 作用于某个线程之内
 */
public class BaseContextUtils {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    /**
     * 设置值
     *
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取值
     *
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
