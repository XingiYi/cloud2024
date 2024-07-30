package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //服务注册与发现
public class Main9526 {
    public static void main(String[] args) {
        SpringApplication.run(Main9526.class,args);
    }
}
