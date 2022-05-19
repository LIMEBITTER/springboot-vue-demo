package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @TableName volunteer
 */
@Data
@TableName("volunteer")
public class Volunteer {
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


}
