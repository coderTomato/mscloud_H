package com.jd.springcloud.alibaba.service;

import com.jd.springcloud.alibaba.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author lijun
 * @create 2020-03-05 21:22
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
