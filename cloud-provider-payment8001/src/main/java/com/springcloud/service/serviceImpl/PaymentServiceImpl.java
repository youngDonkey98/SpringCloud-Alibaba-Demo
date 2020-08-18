package com.springcloud.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.dao.PaymentDao;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    //@Autowired
    private PaymentDao paymentDao;


    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById( Long id){
        return paymentDao.getPaymentById(id);
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),   //当在配置时间窗口内达到此数量的失败后，打开断路，默认20个
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //断路多久以后开始尝试是否恢复，默认10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //出错百分比阈值，当达到此阈值后，开始短路。默认50%
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
