package com.xuxingstudy.dispatch.service.impl;

import com.xuxingstudy.dispatch.entity.DispatchOrder;
import com.xuxingstudy.dispatch.service.DispatchOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xhb
 * @since 2021/8/10
 */
@SpringBootTest
class DispatchOrderServiceImplTest {

    @Resource
    private DispatchOrderService dispatchOrderService;

    @Test
    void save(){
        DispatchOrder dispatchOrder = new DispatchOrder();
        dispatchOrder.setOrderId(1);
        dispatchOrder.setOrderContent("cfgv");
        dispatchOrder.setStatus(0);
        dispatchOrderService.save(dispatchOrder);
    }
}