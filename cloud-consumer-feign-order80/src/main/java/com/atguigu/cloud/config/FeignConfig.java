package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer(){
        //
        return Retryer.NEVER_RETRY; //feign默认配置是不开启重试策略的

        //最大请求次数为3(1+2),初始时间间隔为100ms,重试间最大间隔时间为1s
//        return new Retryer.Default(100,1,3);
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
