package com.cn.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(Orders)实体类
 *
 * @author makejava
 * @since 2022-05-14 20:54:55
 */
@Data
public class Orders {
    private static final long serialVersionUID = -54917492477729899L;
    /**
     * 主键
     */
    private Long id;
    private String userName;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;
    /**
     * 下单用户
     */
    private Long userId;
    /**
     * 地址id
     */
    private Long addressBookId;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 结账时间
     */
    private Date checkoutTime;
    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;
    /**
     * 实收金额
     */
    private Double amount;
    /**
     * 备注
     */
    private String remark;

    private String phone;

    private String address;


    private String consignee;


}

