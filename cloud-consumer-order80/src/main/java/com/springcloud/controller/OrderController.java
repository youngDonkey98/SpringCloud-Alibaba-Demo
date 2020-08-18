package com.springcloud.controller;



import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    // 远程地址
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;      // 负责远程调用（即：分布式开发） 通过 RestTemplate 微服务A 调用 微服务B

    @PostMapping("/consumer/payment/create") // 游览器调用 自定义 即url访问路径
    public CommonResult<Payment> create(Payment payment){
        /*
            第一个参数 ： 远程地址 与 远程接口映射地址保持一致
            第二个参数 ： 请求数据 放置在请求体中国
            第三个参数 ： 远程返回的数据结果
         */
        CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return result;
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        return result;
    }

    //==> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }


}
