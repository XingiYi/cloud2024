package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    //重试机制
    @Bean
    public Retryer myRetryer(){
        //默认不再次请求
//        return Retryer.NEVER_RETRY;
        //请求时间间隔100ms,最大请求时间间隔1s,最大请求次数3(1+2)
        return new Retryer.Default(100,1,3);
    }

    //开启日志打印功能
    @Bean
    public Logger.Level myLoggerLevel(){
        return Logger.Level.FULL;
    }

}
