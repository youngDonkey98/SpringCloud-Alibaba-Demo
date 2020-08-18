package com.springcloud.controller;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    PaymentFeignService paymentFeignService; // 远程接口调用 创建JDK的代理对象

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment>getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> res = paymentFeignService.getPaymentById(id);
        return res;
    }

}
