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
     * 添加失败
     *
     * @return
     */
    public static Result saveErr() {
        return new Result(Code.SAVE_ERR, null, "添加失败");
    }

    /**
     * 添加失败
     *
     * @return
     */
    public static Result saveOK() {
        return new Result(Code.SAVE_OK, null, "添加成功");
    }

    /**
     * 删除失败
     *
     * @return
     */
    public static Result deleteErr() {
        return new Result(Code.DELETE_ERR, null, "删除失败");
    }

    /**
     * 删除成功-20021
     *
     * @return
     */
    public static Result deleteOK() {
        return new Result(Code.DELETE_OK, null, "删除成功");
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

    /**
     * 查询失败
     *
     * @return
     */
    public static Result getErr() {
        return new Result(Code.GET_ERR, null, "查询失败");
    }

    /**
     * 查询成功
     *
     * @return
     */
    public static Result getOK() {
        return new Result(Code.GET_OK, null, "查询成功");
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static Result postErr() {
        return new Result(Code.POST_ERR, null, "请求失败");
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static Result postOK() {
        return new Result(Code.POST_OK, null, "请求成功");
    }
}
