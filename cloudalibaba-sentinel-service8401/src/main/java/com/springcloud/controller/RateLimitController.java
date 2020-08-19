package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RateLimitController{


    // blockHandler : 指定降级处理的方法 当请求不符合sentinel后台  配置  规则 就会走此降级方法
    // fallback : 指定降级处理的方法 当产生异常时 就走此降级方法
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException",blockHandlerClass = CustomerBlockHandler.class)
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    // 降级处理方法 要与 业务方法 声明 保持一致（参数 和 返回结果 保持一致 最后一个参数 需要指定BlockException类型）
//    public CommonResult handleException(BlockException exception){
//        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
//    }


}
