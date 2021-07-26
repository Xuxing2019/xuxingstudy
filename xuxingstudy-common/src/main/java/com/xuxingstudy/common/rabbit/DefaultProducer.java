package com.xuxingstudy.common.rabbit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author xhb
 * @since 2021/7/25
 */
@Slf4j
@AllArgsConstructor
public class DefaultProducer implements Producer{

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Carrier carrier) {
        try {
            rabbitTemplate.convertAndSend(carrier.getExchangeName(),carrier.getRoutingKeyName(), carrier.getContent());
        } catch (AmqpException e) {
            log.error("rabbitSendMsgException carrier={} msg={}", carrier , e.getMessage());
        }
    }

    @Override
    public void send(Carrier carrier, long delayTime) {
        // TODO 延时消息发送
    }
}
