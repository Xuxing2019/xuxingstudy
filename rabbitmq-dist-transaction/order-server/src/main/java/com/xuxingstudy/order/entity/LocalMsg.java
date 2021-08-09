package com.xuxingstudy.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xhb
 * @since 2021/8/9
 */
@Data
public class LocalMsg {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String content;

    /**
     * 消息发送状态 0 发送中 1 发送成功
     */
    private Integer status;

    private Integer retryCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
