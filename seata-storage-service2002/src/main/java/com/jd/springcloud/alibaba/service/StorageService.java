package com.jd.springcloud.alibaba.service;

/**
 * @author lijun
 * @create 2020-03-15 20:24
 */
public interface StorageService {

    //扣减库存
    void descrease(Long productId, Integer count);
}
