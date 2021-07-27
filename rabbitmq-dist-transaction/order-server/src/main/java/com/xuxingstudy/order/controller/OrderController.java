package com.xuxingstudy.order.controller;


import com.alibaba.fastjson.JSON;
import com.xuxingstudy.order.entity.Order;
import com.xuxingstudy.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xuxing
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/order/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<String> createOrder(){
        orderService.create();
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity<String> list(){
        List<Order> list = orderService.list();
        return ResponseEntity.ok(JSON.toJSONString(list));
    }
}
