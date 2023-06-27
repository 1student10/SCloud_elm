package com.elm.feign;

import com.elm.po.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class FoodFeignClientCallBack implements FoodFeignClient{
    @Override
    public CommonResult listFoodByBusinessId(Integer businessId) {
        //返回降级响应（食品信息返回null）
        return new CommonResult(403,"fegin触发了熔断降级",null);
    }
}

