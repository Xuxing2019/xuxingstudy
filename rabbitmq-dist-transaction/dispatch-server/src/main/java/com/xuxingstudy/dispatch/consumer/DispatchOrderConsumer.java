package com.xuxingstudy.dispatch.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *      派单
 * </p>
 *
 * @author xhb
 * @since 11:53 2021/7/26
 */
@Slf4j
@Component
@AllArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        exchange = @Exchange(value = "dispatchOrderDirect"),
        value = @Queue(value = "dispatchOrderQueue", durable = "true"),
        key = {"dispatchOrderRoutingKey"}
), concurrency = "10")
public class DispatchOrderConsumer {

    @RabbitHandler
    private void handler(Carrier<DispatchOrderModel> carrier, Channel channel, Message message){
        DispatchOrderModel dispatchOrderModel = carrier.getContent();
        log.info("dispatchOrderModel={}", JSON.toJSONString(dispatchOrderModel));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
