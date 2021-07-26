package com.xuxingstudy.order.service.impl;

import com.xuxingstudy.common.rabbit.Producer;
import com.xuxingstudy.order.common.DispatchOrderModel;
import com.xuxingstudy.order.entity.XuxingOrder;
import com.xuxingstudy.order.mapper.XuxingOrderMapper;
import com.xuxingstudy.order.model.DispatchOrderCarrier;
import com.xuxingstudy.order.service.IXuxingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuxing
 * @since 2021-07-26
 */
@Slf4j
@Service
public class XuxingOrderServiceImpl extends ServiceImpl<XuxingOrderMapper, XuxingOrder> implements IXuxingOrderService {

    @Resource
    private Producer producer;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder() {
        try {
            XuxingOrder xuxingOrder = new XuxingOrder();
            Random random = new Random();
            xuxingOrder.setOrderId(random.nextInt());
            xuxingOrder.setUserId(random.nextInt());
            xuxingOrder.setOrderContent("订单内容");
            xuxingOrder.setCreateTime(LocalDateTime.now());
            save(xuxingOrder);
            DispatchOrderModel dispatchOrderModel = new DispatchOrderModel();
            BeanUtils.copyProperties(xuxingOrder, dispatchOrderModel);
            // 发送派单需求
            DispatchOrderCarrier dispatchOrderCarrier = DispatchOrderCarrier.builder()
                    .exchangeName("dispatchOrderDirect")
                    .routingKey("dispatchOrderRoutingKey")
                    .queueName("dispatchOrderQueue")
                    .content(dispatchOrderModel)
                    .build();
            producer.send(dispatchOrderCarrier);
        } catch (Exception e) {
            log.error("createOrderException msg={}", e.getMessage());
        }
    }
}
