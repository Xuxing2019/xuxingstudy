package com.xuxingstudy.order.mapper;

import com.xuxingstudy.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
