package com.xuxingstudy.order.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuxing
 * @since 2021-07-26
 */
@RestController
@RequestMapping("/order")
public class XuxingOrderController {

    @PostMapping("create")
    public ResponseEntity<String> createOrder(){
        return ResponseEntity.ok("success");
    }
}
