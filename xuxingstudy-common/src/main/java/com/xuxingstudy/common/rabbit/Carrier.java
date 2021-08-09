package com.xuxingstudy.common.rabbit;

import lombok.*;

import java.io.Serializable;

/**
 * @author xhb
 * @since 2021/7/25
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrier<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交换机名称
     *
     * @return String
     */
    private String exchangeName;

    /**
     * 队列名称
     *
     * @return String
     */
    private String queueName;

    /**
     * 路由Key
     *
     * @return String
     */
    private String routingKey;

    /**
     * 消息体
     *
     * @return 消息内容
     */
    T content;
}
