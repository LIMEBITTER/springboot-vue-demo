package com.example.springboot.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.springboot.entity.Volunteer;
import lombok.Data;

/**
 * 志愿者页面返回的对象
 */
@Data
public class VolunteerDto  {

//    /**
//     * 居民id
//     */
//    private int id;
//
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



    /**
     * 志愿者id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    /**
     * 志愿者工作时间
     */
    private double workTime;

    /**
     * 工作地点
     */
    private String workAddress;

    /**
     * 居民id
     *
     */
    private int rid;

    /**
     * 统计当前工作地点的人数
     */
    private int countAddressNum;
}
