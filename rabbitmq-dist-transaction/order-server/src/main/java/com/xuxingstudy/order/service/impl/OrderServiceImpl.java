package com.xuxingstudy.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.entity.Order;
import com.xuxingstudy.order.mapper.OrderMapper;
import com.xuxingstudy.order.service.LocalMsgService;
import com.xuxingstudy.order.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    @Qualifier("reliableRabbitTemplate")
    private RabbitTemplate reliableRabbitTemplate;

    @Resource
    private LocalMsgService localMsgService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create() {
        Order order = new Order();
        order.setUserId(6676);
        order.setOrderContent("aabbcc");
        boolean save = save(order);
        if (save) {
            DispatchOrderModel dispatchOrderModel = new DispatchOrderModel();
            BeanUtils.copyProperties(order, dispatchOrderModel);
            // 发送派单需求
            Carrier<DispatchOrderModel> dispatchOrderModelCarrier = new Carrier<>();
            dispatchOrderModelCarrier.setExchangeName("dispatchOrderDirect");
            dispatchOrderModelCarrier.setRoutingKey("dispatchOrderRoutingKey");
            dispatchOrderModelCarrier.setQueueName("dispatchOrderQueue");
            dispatchOrderModelCarrier.setContent(dispatchOrderModel);
            // 保存本地消息
            LocalMsg localMsg = new LocalMsg();
            localMsg.setContent(JSON.toJSONString(dispatchOrderModelCarrier));
            localMsg.setStatus(0);
            localMsg.setCreateTime(LocalDateTime.now());
            localMsg.setUpdateTime(LocalDateTime.now());
            localMsgService.save(localMsg);
            // 异步派单
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(localMsg.getId()));
            reliableRabbitTemplate.convertAndSend(dispatchOrderModelCarrier.getExchangeName(), dispatchOrderModelCarrier.getRoutingKey(), dispatchOrderModelCarrier, correlationData);
        }
    }
}
