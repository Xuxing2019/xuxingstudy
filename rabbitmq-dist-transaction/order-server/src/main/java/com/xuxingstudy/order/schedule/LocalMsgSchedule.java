package com.xuxingstudy.order.schedule;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.service.LocalMsgService;
import com.xuxingstudy.order.service.OrderService;
import org.apache.tomcat.jni.Local;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 本地消息重发监听
 * </p>
 *
 * @author xhb
 * @since 18:12 2021/7/27
 */
@Component
public class LocalMsgSchedule {

    private static final Integer RETRY_LIMIT = 3;

    @Resource
    private LocalMsgService localMsgService;

    @Resource
    @Qualifier("reliableRabbitTemplate")
    private RabbitTemplate reliableRabbitTemplate;

    @Scheduled(cron = "0/10 * * * * ? *")
    public void msgReSend() {
        // 查询出发送失败消息
        List<LocalMsg> list = localMsgService.list(Wrappers.<LocalMsg>lambdaQuery().eq(LocalMsg::getStatus, 0));
        list.forEach(localMsg -> {
            Integer retryCount = localMsg.getRetryCount();
            if (retryCount <= RETRY_LIMIT) {
                DispatchOrderModel dispatchOrderModel = JSON.parseObject(localMsg.getContent(), DispatchOrderModel.class);
                Carrier<DispatchOrderModel> dispatchOrderModelCarrier = new Carrier<>();
                dispatchOrderModelCarrier.setExchangeName("dispatchOrderDirect");
                dispatchOrderModelCarrier.setRoutingKey("dispatchOrderRoutingKey");
                dispatchOrderModelCarrier.setQueueName("dispatchOrderQueue");
                dispatchOrderModelCarrier.setContent(dispatchOrderModel);
                CorrelationData correlationData = new CorrelationData();
                correlationData.setId(String.valueOf(localMsg.getId()));
                reliableRabbitTemplate.convertAndSend(dispatchOrderModelCarrier.getExchangeName(), dispatchOrderModelCarrier.getRoutingKey(), dispatchOrderModelCarrier, correlationData);
            } else {
                localMsgService.update(Wrappers.<LocalMsg>lambdaUpdate().set(LocalMsg::getStatus, 2).eq(LocalMsg::getId, localMsg.getId()));
            }
        });
    }
}
