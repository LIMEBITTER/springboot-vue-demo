package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.controller.dto.TravelDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Travel;
import com.example.springboot.mapper.TravelMapper;
import com.example.springboot.mapper.VolunteerMapper;
import com.example.springboot.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 居民出行服务实现类
 */
@Service
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements TravelService{

    @Autowired
    private TravelMapper travelMapper;


    @Override
    public List<TravelDto> getAllTravel() {
        return travelMapper.getAllTravelInfo();
    }

    @Override
    public IPage<TravelDto> getAllLocalTravel(Page page) {
        return  travelMapper.getAllLocalTravelInfo(page);
    }

    @Override
    public IPage<TravelDto> getAllNonTravel(Page page) {
        return travelMapper.getAllNonTravelInfo(page);
    }

    @Override
    public IPage<TravelDto> getTravelDto(Wrapper<TravelDto> wrapper,IPage page) {
        return travelMapper.getTravelDto(wrapper,page);
    }

    @Override
    public IPage<TravelDto> getNonTravelDto(Wrapper<TravelDto> wrapper,IPage page) {
        return travelMapper.getNonTravelDto(wrapper,page);
    }

    @Override
    public int save2(Travel travel) {
        return travelMapper.save2(travel);
    }

    @Override
    public boolean saveResidentTravel(Travel travel) {
        return travelMapper.saveResidentTravel(travel);
    }

//    @Override
//    public IPage<TravelDto> getNonTravelDto(Wrapper<TravelDto> wrapper, Page page) {
//        return travelMapper.getNonTravelDto(wrapper,page);
//    }
}
