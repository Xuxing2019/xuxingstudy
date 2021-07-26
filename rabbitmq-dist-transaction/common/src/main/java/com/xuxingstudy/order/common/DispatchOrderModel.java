package com.xuxingstudy.order.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *     派单模型
 * </p>
 *
 * @author xhb
 * @since 14:27 2021/7/26
 */
@Data
public class DispatchOrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单主键
     */
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
    private LocalDateTime createTime;
}
