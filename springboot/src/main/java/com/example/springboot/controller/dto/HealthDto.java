package com.example.springboot.controller.dto;

import com.example.springboot.entity.Health;
import lombok.Data;

/**
 * 健康页面返回的对象
 */
@Data
public class HealthDto {
    /**
     * 居民id
     */
//    private int id;

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
//    private int rid;
    /**
     * 健康号
     */
    private int id;

    /**
     * 健康状态
     */
    private String normal;

    /**
     * 体温
     */
    private double temperature;

    /**
     * 疫苗接种情况
     */
    private String vaccines;

    private int rid;

    /**
     * 统计当前体温的人数
     */
    private int countTempNum;

    /**
     * 统计当前隔离状态是的人数
     *
     */
    private int countNormalNum;

}
