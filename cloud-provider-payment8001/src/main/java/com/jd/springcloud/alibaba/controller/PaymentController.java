package com.jd.springcloud.alibaba.controller;

import com.jd.springcloud.alibaba.entities.CommonResult;
import com.jd.springcloud.alibaba.entities.Payment;
import com.jd.springcloud.alibaba.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author lijun
 * @create 2020-03-05 21:27
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient client;

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi I am paymentzipkin server fallback, welcome to beijing";
    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        List<String> services = client.getServices();
        for (String ele : services) {
            log.info("***ele:"+ele);
        }
        List<ServiceInstance> instances = client.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return client;
    }

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }
        else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }
    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){


        Payment payment = paymentService.getPaymentById(id);
        log.info("****插入结果："+payment+"\t");
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID:"+id, null);
        }
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
        return serverPort;
    }

}
