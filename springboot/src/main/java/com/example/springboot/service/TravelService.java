package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.TravelDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Travel;
import com.example.springboot.mapper.VolunteerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 居民出行服务
 */
public interface TravelService extends IService<Travel> {


    public List<TravelDto> getAllTravel();

    public IPage<TravelDto> getAllLocalTravel(Page page);

    public IPage<TravelDto> getAllNonTravel(Page page);



    public IPage<TravelDto> getTravelDto(Wrapper<TravelDto> wrapper,IPage page);

    public IPage<TravelDto> getNonTravelDto(Wrapper<TravelDto> wrapper,IPage page);


    int save2(Travel travel);

    boolean saveResidentTravel(Travel travel);
}
