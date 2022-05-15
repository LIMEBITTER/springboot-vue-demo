package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.HealthDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Health;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface HealthMapper extends BaseMapper<Health> {

    @Select("SELECT resident.id,resident.`name`,resident.sex,resident.age,resident.address,health.normal,health.temperature,health.vaccines\n" +
            "FROM resident,health WHERE health.id=resident.id")
    List<HealthDto> getAllHealth();
    @Select("SELECT * FROM health h,resident r where h.id=r.id")
    IPage<HealthDto> getAllHealthInfo(Page page);

    @Select("SELECT * FROM health h,resident r ${ew.customSqlSegment} and h.id=r.id")
    IPage<HealthDto> getHealthDto(@Param(Constants.WRAPPER) Wrapper<HealthDto> wrapper,IPage page);



}
