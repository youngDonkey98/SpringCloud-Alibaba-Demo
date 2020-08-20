package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //实验一： 没有配置
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //实验二：blockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value="fallback",blockHandler="blockHandler")//实验三：blockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value="fallback",fallback = "handlerFallback",blockHandler="blockHandler") //实验4.fallback和blockHandler同时存在如何处理？
    //allback和blockHandler都配置：不超过降级规则执行fallback兜底处理；超过降级规则抛BlockException异常，被blockHandler处理

    @SentinelResource(value="fallback",
            fallback = "handlerFallback",
            blockHandler="blockHandler",
            exceptionsToIgnore = IllegalArgumentException.class) //实验5.忽略特定异常，不进行fallback降级处理，但是，如果违规，依然走blockHandler降级处理
    public CommonResult<Payment> fallback(@PathVariable("id") Integer id) {
        //注意参数传递，站位符
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/{id}", CommonResult.class,id);
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    //fallback 针对 业务代码异常情况 降级处理
    public CommonResult<Payment> handlerFallback(@PathVariable("id")  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<Payment>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    //blockHandler  针对于sentinel控制配置规则违规 降级处理
    public CommonResult<Payment> blockHandler(@PathVariable("id")  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<Payment>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }


}
