package com.example.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.controller.VolunteerController;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Volunteer;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.VolunteerMapper;
import com.example.springboot.service.VolunteerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Autowired
    private VolunteerController controller;

    @Test
    void contextLoads() {



    }

}
