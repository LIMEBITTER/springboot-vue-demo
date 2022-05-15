package com.example.springboot.controller.dto;

import com.example.springboot.entity.Volunteer;
import lombok.Data;

/**
 * 志愿者页面返回的对象
 */
@Data
public class VolunteerDto extends Volunteer {

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
     * 电话
     */
    private String tel;

}
