package com.springcloud.service;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")    // 通过Feign调用哪一个远程服务 指定服务名称
public interface PaymentFeignService {

    // 调用哪一个接口  方法的声明 要与被调用的服务的接口的方法一致
    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

}
