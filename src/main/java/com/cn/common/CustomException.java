package com.cn.common;

/**
 * @author 安珮军
 * @version 1.0
 * @Description: 自定义业务异常
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
