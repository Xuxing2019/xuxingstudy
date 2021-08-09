package com.xuxingstudy.dispatch.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.dispatch.entity.DispatchOrder;
import com.xuxingstudy.dispatch.service.DispatchOrderService;
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
), concurrency = "10-50")
public class DispatchOrderConsumer {

    private static Integer testFailRetryCount = 1;

    private final DispatchOrderService dispatchOrderService;

    @RabbitHandler
    private void handler(Carrier<DispatchOrderModel> carrier, Channel channel, Message message){
        DispatchOrderModel dispatchOrderModel = carrier.getContent();
        log.info("dispatchOrderModel={}", JSON.toJSONString(dispatchOrderModel));
        try {
            // 执行操作 TODO 注意保证操作的幂等性
            DispatchOrder dispatchOrder = new DispatchOrder();
            dispatchOrder.setOrderId(dispatchOrderModel.getOrderId());
            dispatchOrder.setOrderContent(dispatchOrderModel.getOrderContent());
            dispatchOrder.setStatus(0);
            dispatchOrder.setUserId(dispatchOrderModel.getUserId());
            boolean save = dispatchOrderService.save(dispatchOrder);
            if (save) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else if (testFailRetryCount++ <= 5){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            // TODO 进入死信队列
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
