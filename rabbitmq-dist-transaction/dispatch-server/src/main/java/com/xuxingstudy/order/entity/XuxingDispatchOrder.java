package com.xuxingstudy.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuxing
 * @since 2021-07-26
 */
@TableName("xuxing_dispatch_order")
public class XuxingDispatchOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运单主键
     */
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
    private LocalDateTime createTime;

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "XuxingDispatchOrder{" +
            "dispatchId=" + dispatchId +
            ", orderId=" + orderId +
            ", status=" + status +
            ", orderContent=" + orderContent +
            ", userId=" + userId +
            ", createTime=" + createTime +
        "}";
    }
}
