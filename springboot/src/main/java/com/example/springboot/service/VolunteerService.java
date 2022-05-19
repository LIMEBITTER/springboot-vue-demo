package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Volunteer;

import java.util.List;

/**
 * 志愿者服务
 */
public interface VolunteerService extends IService<Volunteer> {
    /**
     * 查询志愿者dto
     * @return
     */
    List<VolunteerDto> getAllVolunteer();


    IPage<VolunteerDto>getAllVolunteer(Page page);

    /**
     * 志愿者分页
     * @param wrapper
     * @param page
     * @return
     */
    IPage<VolunteerDto> getAllVolunteerDto(Wrapper<VolunteerDto> wrapper, IPage page);


    int save2(VolunteerDto volunteerDto);

    boolean updateUserVId(int id, int rid);

    List<VolunteerDto> getVolAddressChart();

    Integer selectVolById(Integer id);

    boolean changeVStatus(Integer id);
}
