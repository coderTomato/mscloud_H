package com.jd.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author lijun
 * @create 2020-03-08 23:45
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallbackService paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---PaymentFallbackService paymentInfo_Timeout";
    }
}
