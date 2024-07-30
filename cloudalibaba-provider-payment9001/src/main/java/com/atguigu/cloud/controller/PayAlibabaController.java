package com.atguigu.cloud.controller;


import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry, serverPort: " + serverPort + "\t id " + id;
    }

    //openfeign+sentinel进行服务降级和流量监控的整合处理case
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        //模拟从数据库查询出数据并赋值给DTO
        PayDTO payDTO = new PayDTO();

        payDTO.setId(1024);
        payDTO.setOrderNo(orderNo);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:" + IdUtil.fastUUID());
        payDTO.setUserId(1);

        return ResultData.success("查询返回值"+payDTO);
    }

    public ResultData handlerBlockHandler(@PathVariable("orderNo") String orderNo, BlockException exception){
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "getPayByOrderNo服务不可用," +
                "触发sentinel流控配置规则" + "\t" + "/(ㄒoㄒ)/~~");
    }

    //测试openfeign+sentinel实现服务降级和流量监控
    @GetMapping("/pay/get/{order}")
    @SentinelResource(value = "getOrderInfo",blockHandler = "getOrderInfoHandler")
    public ResultData getOrderInfo(@PathVariable("order") String order) {
        //模拟订单查询,返回一个订单信息
        PayDTO payDTO = new PayDTO();
        payDTO.setId(10086);
        payDTO.setUserId(132131);
        payDTO.setAmount(BigDecimal.valueOf(19.9));
        payDTO.setOrderNo(order);
        payDTO.setPayNo("支付流水号" + IdUtil.fastUUID());

        return ResultData.success("查询返回值" + payDTO);
    }
    public ResultData getOrderInfoHandler(@PathVariable("order") String order,BlockException blockException){
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "getOrderInfo不可用,触发sentinel流控配置规则");
    }
}
