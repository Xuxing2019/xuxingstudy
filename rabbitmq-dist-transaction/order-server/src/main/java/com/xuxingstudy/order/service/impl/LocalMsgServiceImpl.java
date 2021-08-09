package com.xuxingstudy.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxingstudy.common.rabbit.Carrier;
import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.entity.Order;
import com.xuxingstudy.order.mapper.LocalMsgMapper;
import com.xuxingstudy.order.mapper.OrderMapper;
import com.xuxingstudy.order.service.LocalMsgService;
import com.xuxingstudy.order.service.OrderService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class LocalMsgServiceImpl extends ServiceImpl<LocalMsgMapper, LocalMsg> implements LocalMsgService {

}
