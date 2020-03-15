package com.jd.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author lijun
 * @create 2020-03-15 21:28
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(Long userId, BigDecimal money);
}
