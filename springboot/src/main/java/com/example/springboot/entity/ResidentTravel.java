package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("resident_travel")
public class ResidentTravel {
    /**
     * 居民id
     */
    private int rid;

    /**
     * 出行id
     */
    private int travelId;
}
