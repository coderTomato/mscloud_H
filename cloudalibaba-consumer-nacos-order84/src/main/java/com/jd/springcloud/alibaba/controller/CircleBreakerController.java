package com.jd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jd.springcloud.alibaba.entities.CommonResult;
import com.jd.springcloud.alibaba.entities.Payment;
import com.jd.springcloud.alibaba.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;


@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value="fallback",fallback = "handlerFallback") //fallback  只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")
    @SentinelResource(value="fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if(result.getData() == null){
            throw new NullPointerException("NullPointerException, 该ID 没有对应记录，空指针异常");
        }
        return result;
    }
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable Long id, Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback,exception内容"+e.getMessage(),payment);
    }

    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult(445, "blockHandler-sentinel限流，无此流水"+blockException.getMessage());
    }

    //==============OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value="/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
