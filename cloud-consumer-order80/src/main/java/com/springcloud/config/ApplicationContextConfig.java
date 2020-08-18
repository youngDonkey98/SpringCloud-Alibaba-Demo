package com.springcloud.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration  // 声明这是一个配置类 或者使用注解@Configuration
public class ApplicationContextConfig {


    // 声明一个bean对象
    // RestTemplate 用于远程调用的模板类
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }



}
