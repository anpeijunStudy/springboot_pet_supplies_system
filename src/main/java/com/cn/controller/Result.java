package com.cn.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回统一数据
 *
 * @author springboot_pet_supplies_system
 * @since 2022-05-03 16:37:21
 */
@Getter
@Setter
public class Result {
    //描述统一格式中的编码，用于区分操作，可以简化配置0或1表示成功失败
    private Integer code;
    //描述统一格式中的数据
    private Object data;
    //描述统一格式中的消息，可选属性
    private String msg;

    public Result() {
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
