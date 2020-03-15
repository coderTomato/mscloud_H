package com.jd.springcloud.alibaba.service.impl;

import com.jd.springcloud.alibaba.dao.StorageDao;
import com.jd.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author lijun
 * @create 2020-03-15 20:29
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;

    //扣减库存
    @Override
    public void descrease(Long productId, Integer count) {
        LOGGER.info("------> storage-service中扣减库存开始");
        storageDao.decrease(productId, count);
        LOGGER.info("------> storage-service中扣减库存结束");
    }
}
