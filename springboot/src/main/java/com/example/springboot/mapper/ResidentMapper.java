package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.ResidentDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface ResidentMapper extends BaseMapper<Resident> {

    //本小区人员信息和分页信息
    @Select("SELECT * FROM resident ${ew.customSqlSegment} ")
    IPage<Resident> getLocalResident(@Param(Constants.WRAPPER) Wrapper<Resident> wrapper, IPage page);

    @Select("select  * from resident where local_people='是'")
    IPage<Resident> selectLocalResident(Page page);

    //非小区人员信息和分页信息
//    @Select("SELECT * FROM resident ${ew.customSqlSegment} ")
//    List<Resident> getNonLocalResident(@Param(Constants.WRAPPER) Wrapper<Resident> wrapper);

    @Select("select  * from resident where local_people='否'")
    IPage<Resident> selectNonLocalResident(Page page);

    /**
     * volunteerDto插入/修改所需信息 id name sex age tel
     */
    Integer saveVolunteerDtoInfo(VolunteerDto volunteerDto);

    //根据id查询居民信息
    Resident selectResidentById(int id);

    List<Resident> getAllPerson();

    @Insert("insert into resident(id,name,sex,age,address,tel,local_people,resident_status) VALUES(#{id},#{name},#{sex},#{age},#{address},#{tel},#{local_people},1)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save2(ResidentDto residentDto);

    @Update("update user set rid=#{id} where id = #{uid}")
    boolean updateUserRId(ResidentDto residentDto);

    @Select("SELECT * from resident where id = (select rid from user where id = #{id}) and resident_status=2")
    Resident bindUserResident(Integer id);

    @Update("update resident set resident_status = 2 where id = #{id}")
    boolean changeRStatus(Integer id);

    @Select("SELECT * from resident where id = (select rid from user where id = #{id})")
    Resident selectResidentStatus(Integer id);

    @Select("SELECT local_people,count(local_people) as countTypeOfPeople from resident GROUP BY local_people")
    List<ResidentDto> getTypeOfPeople();
}
