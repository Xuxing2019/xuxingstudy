package com.xuxingstudy.dispatch.service.impl;

import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.dispatch.constant.XuxingDispatchOrderStateConst;
import com.xuxingstudy.dispatch.entity.XuxingDispatchOrder;
import com.xuxingstudy.dispatch.mapper.XuxingDispatchOrderMapper;
import com.xuxingstudy.dispatch.service.IXuxingDispatchOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class XuxingDispatchOrderServiceImpl extends ServiceImpl<XuxingDispatchOrderMapper, XuxingDispatchOrder> implements IXuxingDispatchOrderService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDispatchOrder(DispatchOrderModel dispatchOrderModel) {
        XuxingDispatchOrder xuxingDispatchOrder = new XuxingDispatchOrder();
        Random random = new Random();
        xuxingDispatchOrder.setDispatchId(random.nextInt());
        xuxingDispatchOrder.setOrderContent(dispatchOrderModel.getOrderContent());
        xuxingDispatchOrder.setStatus(XuxingDispatchOrderStateConst.State.DISPATCHING);
        xuxingDispatchOrder.setUserId(dispatchOrderModel.getUserId());
        xuxingDispatchOrder.setCreateTime(LocalDateTime.now());
        save(xuxingDispatchOrder);
    }
}
