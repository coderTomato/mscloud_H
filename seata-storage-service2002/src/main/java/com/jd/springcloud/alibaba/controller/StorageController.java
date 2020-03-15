package com.jd.springcloud.alibaba.controller;

import com.jd.springcloud.alibaba.domain.CommonResult;
import com.jd.springcloud.alibaba.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lijun
 * @create 2020-03-15 20:36
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    //扣减库存
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){
        storageService.descrease(productId, count);
        return new CommonResult(200,"扣减库存成功!");
    }
}
