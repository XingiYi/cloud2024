package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 根据产品id扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
