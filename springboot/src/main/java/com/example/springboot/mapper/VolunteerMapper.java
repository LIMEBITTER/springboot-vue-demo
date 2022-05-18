package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.entity.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface VolunteerMapper extends BaseMapper<Volunteer> {

//    @Select(
//            "SELECT resident.id ,resident.`name`,resident.sex,resident.age,resident.tel,volunteer.work_time,volunteer.work_address\n" +
//                    "FROM volunteer,resident WHERE resident.id=volunteer.id"
//    )
    @Select("SELECT v.id,r.name,r.tel,r.sex,v.workTime,v.workAddress from resident r inner join volunteer v on v.id=r.v_id")
    List<VolunteerDto> getAllVolunteerInfo();

//    @Select("SELECT * FROM volunteer v,resident r where v.id=r.id")
    @Select("SELECT * from resident r inner join volunteer v on v.id=r.v_id")
    IPage<VolunteerDto> getAllVolunteer(Page page);

    @Select("SELECT * FROM volunteer v,resident r ${ew.customSqlSegment} and v.id=r.v_id")
    IPage<VolunteerDto> getVolunteerDto(@Param(Constants.WRAPPER) Wrapper<VolunteerDto> wrapper, IPage page );

    @Insert("INSERT into volunteer(id,workTime,workAddress,volunteerStatus) VALUES(#{id},#{workTime},#{workAddress},1)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save2(VolunteerDto volunteerDto);

    @Update("UPDATE resident set v_id = #{id} where id = #{rid}")
    boolean updateUserVId(int id, int rid);

    @Select("SELECT workAddress,count(workAddress) as countAddressNum from volunteer GROUP BY workAddress")
    List<VolunteerDto> getVolAddressChart();
}
