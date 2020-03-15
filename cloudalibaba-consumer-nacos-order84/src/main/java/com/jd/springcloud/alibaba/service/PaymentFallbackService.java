package com.jd.springcloud.alibaba.service;

import com.jd.springcloud.alibaba.entities.CommonResult;
import com.jd.springcloud.alibaba.entities.Payment;
import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements  PaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
