package com.xuxingstudy.common.rabbit;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author xhb
 * @since 2021/7/25
 */
public interface Carrier<T> extends Serializable {

    /**
     * 交换机名称
     * @return String
     */
    String getExchangeName();

    /**
     * 队列名称
     * @return String
     */
    String getQueueName();

    /**
     * 路由Key
     * @return String
     */
    String getRoutingKey();

    /**
     * 消息体
     * @return 消息内容
     */
    T getContent();
}
