package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 使用lombok 来简化 javaBean开发 使得javaBean结构更加的清晰简单
@Data                   // 自动生成get set方法
@AllArgsConstructor     // 自动生成全参的构造器
@NoArgsConstructor      // 自动生成无参的构造器
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);//如果这行报错，请安装lombok插件
    }


}
