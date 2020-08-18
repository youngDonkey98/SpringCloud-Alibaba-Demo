package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;    // Feign接口 远程调用

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }

    //超时降级演示
//    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="15000")//超过1.5秒就降级自己
//    })
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int age= 1/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:"+result);
        return result;
    }

    //降级方法 声明 与 业务方法 保持一致 （参数和返回结果必须保持一致）
    //兜底方法，上面方法出问题,我来处理，返回一个出错信息
//    public String payment_TimeoutHandler(Integer id) {
//        return "我是消费者80,对方支付系统繁忙请10秒后再试。或自己运行出错，请检查自己。";
//    }
//
////    //下面是全局fallback方法
//    public String payment_Global_FallbackMethod(){
//        return "Global异常处理信息，请稍后再试,(┬＿┬)";
//    }

}
