package com.jd.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijun
 * @create 2020-03-15 19:36
 */
@Configuration
@MapperScan({"com.jd.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
