package com.xuxingstudy.order.model;

import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.order.common.DispatchOrderModel;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *  创建订单派单消息载体
 * </p>
 *
 * @author xhb
 * @since 11:48 2021/7/26
 */
@Data
@Builder
public class DispatchOrderCarrier implements Carrier<DispatchOrderModel> {

    private static final long serialVersionUID = 1L;

    private String exchangeName;

    private String queueName;

    private String routingKey;

    private DispatchOrderModel content;

}
