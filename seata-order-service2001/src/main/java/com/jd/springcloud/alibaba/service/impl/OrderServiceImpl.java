package com.jd.springcloud.alibaba.service.impl;

import com.jd.springcloud.alibaba.dao.OrderDao;
import com.jd.springcloud.alibaba.domain.Order;
import com.jd.springcloud.alibaba.service.AccountService;
import com.jd.springcloud.alibaba.service.OrderService;
import com.jd.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author lijun
 * @create 2020-03-15 17:48
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name="fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("----->开始新建订单");
        //1.新建订单
        orderDao.create(order);

        log.info("----->订单微服务开始调用库存，做扣减开始");
        //2.扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减结束");

        log.info("----->订单微服务开始调用账户，做扣减Money开始");
        //3. 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减Money结束");

        //4.修改订单状态，从0--> 1 代表已经完成
        log.info("-----> 修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------> 修改订单状态结束");

        log.info("------> 下订单结束了");
    }
}
