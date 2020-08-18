package com.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentServiceImpl implements PaymentService {


    //成功
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }


    //超时降级演示
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000") //5秒钟以内就是正常的业务逻辑
    })
    //失败
    public String payment_Timeout(Integer id){
        //int timeNumber = 3;
        int timeNumber = 13;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    //兜底方法，上面方法出问题,我来处理，返回一个出错信息
    public String payment_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" payment_TimeoutHandler,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }



}
