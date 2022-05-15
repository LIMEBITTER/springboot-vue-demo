package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName health
 */
@Data
@TableName("health")
public class Health {
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
}
