package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.TravelDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Travel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface TravelMapper extends BaseMapper<Travel> {

    @Select( "SELECT t.destination,t.totalman,t.travel_tool,r.id,r.`name`,r.age,r.sex\n" +
                    "FROM travel t,resident r")
    List<TravelDto> getAllTravelInfo();
//    @Select("SELECT * FROM travel v,resident r where v.id=r.id and local_people='是'")
    @Select("select t.id,r.id,r.name,r.sex,r.age,t.travelTool,t.destination,t.totalman ,t.travelStatus from resident_travel rt inner join resident r on rt.rid=r.id inner join travel t on rt.traval_id=t.id where local_people = '是'")
    IPage<TravelDto> getAllLocalTravelInfo(Page page);

    @Select("select t.id,r.id,r.name,r.sex,r.age,t.travelTool,t.destination,t.totalman ,t.travelStatus from resident_travel rt inner join resident r on rt.rid=r.id inner join travel t on rt.traval_id=t.id where local_people = '否'")
    IPage<TravelDto> getAllNonTravelInfo(Page page);

//    @Select("SELECT * FROM travel v,resident r where v.id=r.id and local_people='是'${ew.customSqlSegment} ")
    @Select("SELECT * from resident_travel rt inner join resident r on rt.rid=r.id inner join travel t on rt.traval_id=t.id ${ew.customSqlSegment} and r.local_people='是'")
    IPage<TravelDto> getTravelDto(@Param(Constants.WRAPPER) Wrapper<TravelDto> wrapper,IPage page);

//    @Select("SELECT * FROM travel v,resident r ${ew.customSqlSegment} and v.id=r.id  ")
    @Select("SELECT * from resident_travel rt inner join resident r on rt.rid=r.id inner join travel t on rt.traval_id=t.id ${ew.customSqlSegment} and r.local_people='否'")
    IPage<TravelDto> getNonTravelDto(@Param(Constants.WRAPPER) Wrapper<TravelDto> wrapper,IPage page);

    @Insert("INSERT into travel(id,travelTool,destination,totalman,travelStatus) VALUES(#{id},#{travelTool},#{destination},#{totalman},0)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save2(TravelDto travel);

    @Insert("INSERT into resident_travel(rid,traval_id) VALUES(#{rid},#{id})")
    boolean saveResidentTravel(TravelDto travel);

    @Update("update travel set travelStatus = 2 where id = #{id}")
    boolean changeSTtatus(Integer id);

    @Select("SELECT travelTool,count(travelTool) as countNum from travel GROUP BY travelTool")
    List<TravelDto> getTravelToolCount();

    @Select("SELECT travelStatus from travel WHERE id=(SELECT traval_id from resident_travel where rid = #{rid})")
    Integer selectTravelById(Integer rid);
}
