package com.cn.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:
 */
@Data
public class OrdersFront {

    private Integer page;

    private Integer pageSize;

    private Long number;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
