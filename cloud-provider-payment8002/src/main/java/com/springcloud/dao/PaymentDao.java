package com.springcloud.dao;


import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component       //代替@Repository声明bean
@Mapper           //mybatis提供的，等价：@MapperScan("com.springcloud.dao")
//@Repository     //spring提供的。在此，只是为了声明bean对象
public interface PaymentDao {

    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);


}
