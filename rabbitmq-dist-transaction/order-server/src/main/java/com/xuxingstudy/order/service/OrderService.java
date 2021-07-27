package com.xuxingstudy.order.service;

import com.xuxingstudy.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     */
    void create();
}
