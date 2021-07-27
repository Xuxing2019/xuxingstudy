package com.xuxingstudy.dispatch.consumer;

import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.dispatch.entity.XuxingDispatchOrder;
import com.xuxingstudy.dispatch.service.IXuxingDispatchOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

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

    private final IXuxingDispatchOrderService iXuxingDispatchOrderService;

    @RabbitHandler
    private void handler(Carrier<DispatchOrderModel> carrier){
        DispatchOrderModel dispatchOrderModel = carrier.getContent();
        iXuxingDispatchOrderService.createDispatchOrder(dispatchOrderModel);
    }
}
