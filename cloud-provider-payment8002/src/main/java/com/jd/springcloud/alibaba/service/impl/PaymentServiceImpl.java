package com.jd.springcloud.alibaba.service.impl;

import com.jd.springcloud.alibaba.dao.PaymentDao;
import com.jd.springcloud.alibaba.entities.Payment;
import com.jd.springcloud.alibaba.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lijun
 * @create 2020-03-05 21:23
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
