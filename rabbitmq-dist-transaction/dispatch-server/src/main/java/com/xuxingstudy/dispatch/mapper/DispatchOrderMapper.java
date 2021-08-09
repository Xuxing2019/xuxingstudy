package com.xuxingstudy.dispatch.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuxingstudy.dispatch.entity.DispatchOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单派送表 Mapper 接口
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@Mapper
public interface DispatchOrderMapper extends BaseMapper<DispatchOrder> {

}
