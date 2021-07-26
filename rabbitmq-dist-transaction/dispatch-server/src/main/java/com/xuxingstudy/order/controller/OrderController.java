package com.xuxingstudy.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhb
 * @since 2021/7/25
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @PostMapping("create")
    public ResponseEntity<String> createOrder(){
        return ResponseEntity.ok("success");
    }
}
