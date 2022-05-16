package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @TableName travel
 */
@Data
@TableName("travel")
public class Travel {
    /**
     * 出行id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private int id;

    /**
     * 出行工具
     */
    private String travelTool;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出行总人数
     */
    private int totalman;

}
