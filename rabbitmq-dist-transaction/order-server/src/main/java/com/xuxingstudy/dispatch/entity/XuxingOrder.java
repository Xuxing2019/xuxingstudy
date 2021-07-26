package com.xuxingstudy.dispatch.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuxing
 * @since 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class XuxingOrder implements Serializable {

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
