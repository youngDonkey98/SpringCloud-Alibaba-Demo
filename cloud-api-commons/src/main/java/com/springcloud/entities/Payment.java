package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 使用lombok 来简化 javaBean开发 使得javaBean结构更加的清晰简单
@Data                   // 自动生成get set方法
@AllArgsConstructor     // 自动生成全参的构造器
@NoArgsConstructor      // 自动生成无参的构造器
//@ToString
//@EqualsAndHashCode
public class Payment implements Serializable {

    private Long id;
    private String serial;


}
