package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //用于向consul为注册中心时注册服务
@EnableFeignClients //启动feign客户端
public class Main80 {
    public static void main(String[] args) {
        SpringApplication.run(Main80.class,args);
    }
}