package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.TravelDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface TravelMapper extends BaseMapper<Travel> {

    @Select( "SELECT t.destination,t.totalman,t.travel_tool,r.id,r.`name`,r.age,r.sex\n" +
                    "FROM travel t,resident r")
    List<TravelDto> getAllTravelInfo();
    @Select("SELECT * FROM travel v,resident r where v.id=r.id and local_people='是'")
    IPage<TravelDto> getAllLocalTravelInfo(Page page);

    @Select("SELECT * FROM travel v,resident r where v.id=r.id and local_people='否'")
    IPage<TravelDto> getAllNonTravelInfo(Page page);

//    @Select("SELECT * FROM travel v,resident r where v.id=r.id and local_people='是'${ew.customSqlSegment} ")
    @Select("SELECT * FROM travel v,resident r ${ew.customSqlSegment} and v.id=r.id")

    IPage<TravelDto> getTravelDto(@Param(Constants.WRAPPER) Wrapper<TravelDto> wrapper,IPage page);

    @Select("SELECT * FROM travel v,resident r ${ew.customSqlSegment} and v.id=r.id  ")
    IPage<TravelDto> getNonTravelDto(@Param(Constants.WRAPPER) Wrapper<TravelDto> wrapper,IPage page);
}