package com.xuxingstudy.common.rabbit;

/**
 * @author xhb
 * @since 2021/7/25
 */
public interface Producer {

    /**
     * 发送普通消息
     * @param carrier 消息载体
     */
    void send(Carrier carrier);

    /**
     * 发送延时消息
     * @param carrier 消息载体
     * @param delayTime 延时时长
     */
    void send(Carrier carrier, long delayTime);
}
