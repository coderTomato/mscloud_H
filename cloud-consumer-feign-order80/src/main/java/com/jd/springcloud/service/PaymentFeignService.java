package com.jd.springcloud.service;

import com.jd.springcloud.entities.CommonResult;
import com.jd.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value="CLOUD-PAYMENT-SERVICE") //需要告诉我找哪个微服务
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout();
}
