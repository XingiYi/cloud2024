package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

@SpringBootApplication
@EnableDiscoveryClient //服务注册与发现
public class Main3377 {
    public static void main(String[] args) {
        SpringApplication.run(Main3377.class,args);
    }
}