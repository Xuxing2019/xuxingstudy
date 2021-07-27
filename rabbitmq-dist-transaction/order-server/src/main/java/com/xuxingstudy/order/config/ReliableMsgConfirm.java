package com.xuxingstudy.order.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.service.LocalMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author xhb
 * @since 15:29 2021/7/27
 */
@Slf4j
@Component
public class ReliableMsgConfirm implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Resource
    private LocalMsgService localMsgService;

    @Resource
    @Qualifier("reliableRabbitTemplate")
    private RabbitTemplate reliableRabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("msg confirm correlationData={} ack={} cause={}", correlationData, ack, cause);
        // 消息发送成功修改本地消息冗余数据
        if (ack) {
            localMsgService.update(Wrappers.<LocalMsg>lambdaUpdate().set(LocalMsg::getStatus, 1).eq(LocalMsg::getId, correlationData.getId()));
        } else {
            // TODO 重发
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        // TODO 交换机消息投递失败回调
    }
}
