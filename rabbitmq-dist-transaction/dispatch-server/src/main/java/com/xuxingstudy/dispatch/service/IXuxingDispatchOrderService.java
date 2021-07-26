package com.xuxingstudy.dispatch.service;

import com.xuxingstudy.dispatch.common.DispatchOrderModel;
import com.xuxingstudy.dispatch.entity.XuxingDispatchOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuxing
 * @since 2021-07-26
 */
public interface IXuxingDispatchOrderService extends IService<XuxingDispatchOrder> {

    /**
     * 创建派送数据
     * @param dispatchOrderModel
     */
    void createDispatchOrder(DispatchOrderModel dispatchOrderModel);
}
