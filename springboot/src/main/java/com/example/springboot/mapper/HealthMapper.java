package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.HealthDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Health;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface HealthMapper extends BaseMapper<Health> {

    @Select("SELECT resident.id,resident.`name`,resident.sex,resident.age,resident.address,health.normal,health.temperature,health.vaccines\n" +
            "FROM resident,health WHERE health.id=resident.health_id")
    List<HealthDto> getAllHealth();
    @Select("SELECT * FROM health h,resident r where r.health_id=h.id")
    IPage<HealthDto> getAllHealthInfo(Page page);

    @Select("SELECT * FROM health h,resident r ${ew.customSqlSegment} and h.id=r.health_id")
    IPage<HealthDto> getHealthDto(@Param(Constants.WRAPPER) Wrapper<HealthDto> wrapper,IPage page);

    @Insert("INSERT INTO health(id,normal,temperature,vaccines,health_status) VALUES(#{id},#{normal},#{temperature},#{vaccines},1)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean save2(HealthDto healthDto);

    @Update("UPDATE resident set health_id = #{id} where id = #{rid}")
    boolean updateHealthId(int id,int rid);

    @Update("UPDATE health set health_status = 2")
    boolean changeHStatus(Integer id);

    @Select("SELECT temperature,count(temperature) as countTempNum from health GROUP BY temperature")
    List<HealthDto> tempCountChart();

    @Select("SELECT normal,count(normal) as countNormalNum from health GROUP BY normal")
    List<HealthDto> getHealthSituation();
}
