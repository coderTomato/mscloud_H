package com.jd.springcloud.cfgbeans;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijun
 * @create 2020-03-08 16:46
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }
}
