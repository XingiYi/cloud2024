package com.atguigu.cloud.apis;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {
    //openfeign+sentinel进行服务降级和流量监控的整合处理case
    @GetMapping("/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);


    //测试openfeign+sentinel实现服务降级和流量监控
    @GetMapping("/pay/get/{order}")
    public ResultData getOrderInfo(@PathVariable("order") String order);

}
