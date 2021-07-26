package com.xuxingstudy.dispatch.controller;


import com.xuxingstudy.dispatch.service.IXuxingOrderService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/order")
public class XuxingOrderController {

    private final IXuxingOrderService iXuxingOrderService;

    @PostMapping("create")
    public ResponseEntity<String> createOrder(){
        iXuxingOrderService.createOrder();
        return ResponseEntity.ok("success");
    }
}
