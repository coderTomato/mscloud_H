package com.jd.springcloud.alibaba;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jd.springcloud.alibaba.entities.CommonResult;
import com.jd.springcloud.alibaba.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444, "按客户自定义, global handlerException-----1",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444, "按客户自定义, global handlerException-----2",new Payment(2020L,"serial003"));
    }
}
