package com.xuxingstudy.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.entity.Order;
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
public interface LocalMsgMapper extends BaseMapper<LocalMsg> {

}
