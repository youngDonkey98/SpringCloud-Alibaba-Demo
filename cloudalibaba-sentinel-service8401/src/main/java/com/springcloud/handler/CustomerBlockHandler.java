package com.springcloud.handler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;


// 自定义降级处理类
// 通过 类名 找到 指定的 降级处理方法
public class CustomerBlockHandler {

    // 处理方法 必须为public 必须为static
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 2");
    }

}
