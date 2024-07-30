package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    public String myCircuitFallback(Integer id, Throwable t) {
        return "服务器繁忙,请耐心等待！";
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "THREADPOOLFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkhead(@PathVariable("id") Integer id) {
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> THREADPOOLFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL 请稍后重试");
    }

    @GetMapping("/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "RatelimitFallback")
    public String Ratelimit(@PathVariable("id") Integer id){
        return payFeignApi.myRatelimit(id);
    }

    public String RatelimitFallback(Integer id,Throwable t){
        return "服务器繁忙,请耐心等待!";
    }
}
