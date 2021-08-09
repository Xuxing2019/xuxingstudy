package com.xuxingstudy.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@Getter
@Setter
@TableName("t_order")
public class Order{

    /**
     * 订单主键
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 订单内容
     */
    private String orderContent;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
