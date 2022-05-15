package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("resident_health")
public class ResidentHealth {
    /**
     * 居民id
     */
    private int rid;
    /**
     * 健康id
     */
    private int healthId;
}
