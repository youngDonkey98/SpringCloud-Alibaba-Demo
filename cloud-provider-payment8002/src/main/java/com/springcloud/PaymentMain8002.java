package com.springcloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient     // 声明当前 微服务 是Eureka的客户端
@EnableTransactionManagement // 开启事务  再去service层添加@Transactional
@MapperScan("com.springcloud.dao")
@SpringBootApplication
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }

}
