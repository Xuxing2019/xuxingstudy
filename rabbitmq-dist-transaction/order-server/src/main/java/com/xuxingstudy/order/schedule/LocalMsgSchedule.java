package com.xuxingstudy.order.schedule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xuxingstudy.order.entity.LocalMsg;
import com.xuxingstudy.order.service.LocalMsgService;
import com.xuxingstudy.order.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  本地消息重发监听
 * </p>
 *
 * @author xhb
 * @since 18:12 2021/7/27
 */
@Component
public class LocalMsgSchedule {

    @Resource
    private LocalMsgService localMsgService;

    @Scheduled(cron = "0/10 * * * * ? *")
    public void msgReSend(){
        List<LocalMsg> list = localMsgService.list(Wrappers.<LocalMsg>lambdaQuery().eq(LocalMsg::getStatus, 0));
        list.forEach(localMsg -> {

        });
    }
}
