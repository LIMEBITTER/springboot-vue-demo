package com.example.springboot.controller.dto;

import com.example.springboot.entity.Travel;
import lombok.Data;

/**
 * 出行传输对象
 */
@Data
public class TravelDto  {
    private int id;
    private String name;
    private String sex;
    private int age;

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

    /**
     * 居民id
     */
    private int rid;

    /**
     * 出行审核状态
     */
    private int travelStatus;


}
