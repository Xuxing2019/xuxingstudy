package com.xuxingstudy.common.rabbit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author xhb
 * @since 2021/7/25
 */
@Slf4j
@AllArgsConstructor
public class DefaultProducer implements Producer,RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Carrier carrier) {
        try {
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            rabbitTemplate.convertAndSend(carrier.getExchangeName(),carrier.getRoutingKey(), carrier.getContent());
        } catch (AmqpException e) {
            log.error("rabbitSendMsgException carrier={} msg={}", carrier , e.getMessage());
        }
    }

    @Override
    public void send(Carrier carrier, long delayTime) {
        // TODO 延时消息发送
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("confirm ack={}", ack);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("returnMsg msg={}", new String(message.getBody()));
    }
}
