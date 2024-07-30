package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{

    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务器宕机或不可用, FallBack服务降级/(ㄒoㄒ)/~~");
    }

    @Override
    public ResultData getOrderInfo(String order) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "请求的服务宕机或不可用,触发降级处理Fallback: " +
                "就算没有那把剑,我也照样可以歼灭敌军！！！");
    }
}
