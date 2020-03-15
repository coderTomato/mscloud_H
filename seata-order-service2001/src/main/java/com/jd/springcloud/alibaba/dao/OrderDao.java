package com.jd.springcloud.alibaba.dao;

import com.jd.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lijun
 * @create 2020-03-15 17:30
 */
@Mapper
public interface OrderDao {

    //创建订单
    void create(Order order);

    //修改订单状态 从0-> 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
