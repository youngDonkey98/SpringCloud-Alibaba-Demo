package com.springcloud.service.serviceImpl;

import com.springcloud.dao.PaymentDao;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
