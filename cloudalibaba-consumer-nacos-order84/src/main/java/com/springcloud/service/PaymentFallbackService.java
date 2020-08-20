package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Component;


@Component      // 降级处理类 是个组件类  不是分层上确切的类 都可以使用component注解来声明bean对象
public class PaymentFallbackService implements PaymentFeignService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
