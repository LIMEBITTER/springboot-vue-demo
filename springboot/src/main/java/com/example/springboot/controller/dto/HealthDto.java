package com.example.springboot.controller.dto;

import com.example.springboot.entity.Health;
import lombok.Data;

/**
 * 健康页面返回的对象
 */
@Data
public class HealthDto extends Health {
    /**
     * 居民id
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 居住地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;

}
