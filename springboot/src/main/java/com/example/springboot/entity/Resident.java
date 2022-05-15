package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @TableName resident
 */
@Data
@TableName("resident")
public class Resident {
    /**
     * 居民id
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 电话
     */
    private String tel;

    /**
     * 是否为本小区居民
     */
    private String localPeople;

    /**
     * 志愿者号
     */
    private int vId;

    /**
     * 健康号
     */
    private int healthId;



}
