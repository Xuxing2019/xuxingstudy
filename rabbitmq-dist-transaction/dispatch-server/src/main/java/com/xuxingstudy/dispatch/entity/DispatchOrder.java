package com.xuxingstudy.dispatch.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 订单派送表
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@Data
@TableName("dispatch_order")
public class DispatchOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运单主键
     */
    @TableId(type = IdType.AUTO)
    private Integer dispatchId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 运单状态
     */
    private Integer status;

    /**
     * 订单内容
     */
    private String orderContent;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
