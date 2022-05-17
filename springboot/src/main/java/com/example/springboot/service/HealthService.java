package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.HealthDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Health;

import java.util.List;

/**
 * 健康服务
 */
public interface HealthService extends IService<Health> {

    List<HealthDto> getAllHealth();

    /**
     * 查询健康dto
     * @return
     */
    IPage<HealthDto> getAllHealthInfo(Page page);

    /**
     * 健康分页
     * @param wrapper
     * @param page
     * @return
     */
    IPage<HealthDto> getAllHealthDto(Wrapper<HealthDto> wrapper,IPage page);

    boolean save2(HealthDto healthDto);

    boolean updateHealthId(int id,int rid);

    boolean changeHStatus(Integer id);

//    boolean save1(Health health);
}
