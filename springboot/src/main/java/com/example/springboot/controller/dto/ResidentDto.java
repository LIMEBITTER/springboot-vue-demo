package com.example.springboot.controller.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * residentDto
 */
@Data
public class ResidentDto {

    /**
     * 用户id
     */
    private int uid;
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
    private String local_people;

    /**
     * 审核状态
     */
    private int resident_status;

    /**
     * 查询是否为本地居民时的人数
     */
    private  int countTypeOfPeople;





}
