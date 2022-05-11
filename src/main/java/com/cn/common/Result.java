package com.cn.common;

import com.cn.code.Code;
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

    /**
     * 修改失败
     *
     * @return
     */
    public static Result updateErr() {
        return new Result(Code.UPDATE_ERR, null, "修改失败");
    }

    /**
     * 修改成功
     *
     * @return
     */
    public static Result updateOK() {
        return new Result(Code.UPDATE_OK, null, "修改成功");
    }
}
