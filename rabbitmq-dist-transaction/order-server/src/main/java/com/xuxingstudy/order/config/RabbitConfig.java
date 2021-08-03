package com.xuxingstudy.order.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author xhb
 * @since 14:06 2021/7/27
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate reliableRabbitTemplate(ConnectionFactory connectionFactory, ReliableMsgConfirm reliableMsgConfirm) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConfirmCallback(reliableMsgConfirm);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

}
