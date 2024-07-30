package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001"; //先写死,硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service"; //服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData add(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getInfo(Integer id){
        return payFeignApi.getPayInfo(id);
    }

    @GetMapping("feign/pay/mylb")
    public String getMylb(){
        return payFeignApi.mylb();
    }

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id")Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info",String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

    //删除/修改作为家庭作业
    //删除
    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData delPayInfo(@PathVariable("id")Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/delete/" + id, ResultData.class, id);
    }

    //修改
    @PutMapping("/consumer/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/update", payDTO, ResultData.class);
    }




}
