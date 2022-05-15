package com.example.springboot.controller.dto;

import com.example.springboot.entity.Travel;
import lombok.Data;

/**
 * 出行传输对象
 */
@Data
public class TravelDto extends Travel {
    private int id;
    private String name;
    private String sex;
    private int age;
}
