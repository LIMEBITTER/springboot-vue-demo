package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.controller.dto.ResidentDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.mapper.ResidentMapper;
import com.example.springboot.mapper.VolunteerMapper;
import com.example.springboot.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 居民信息服务实现类
 */
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, Resident> implements ResidentService{
    @Autowired
    private ResidentMapper residentMapper;


    //(非)小区居民信息
    @Override
    public IPage<Resident> selectAllLocal(Page page) {
        return residentMapper.selectLocalResident(page);
    }

    @Override
    public IPage<Resident> selectAllNonLocal(Page page) {
        return residentMapper.selectNonLocalResident(page);
    }

    @Override
    public List<Resident> getAllPerson() {
        return residentMapper.getAllPerson();
    }

    @Override
    public Resident selectResidentById(int id) {
        return residentMapper.selectResidentById(id);
    }

    //(非)小区居民 分页 信息
    @Override
    public IPage<Resident> selectLocalPeople(Wrapper<Resident> wrapper,IPage page) {
        return residentMapper.getLocalResident(wrapper,page);
    }

    @Override
    public int save2(ResidentDto residentDto) {
        return residentMapper.save2(residentDto);
    }

    @Override
    public boolean updateUserRId(ResidentDto residentDto) {
        return residentMapper.updateUserRId(residentDto);
    }

    @Override
    public Resident bindUserResident(Integer id) {
        return residentMapper.bindUserResident(id);
    }

    @Override
    public boolean changeRStatus(Integer id) {
        return residentMapper.changeRStatus(id);
    }

    @Override
    public Resident selectResidentStatus(Integer id) {
        return residentMapper.selectResidentStatus(id);
    }

    @Override
    public List<ResidentDto> getTypeOfPeople() {
        return residentMapper.getTypeOfPeople();
    }

//    @Override
//    public IPage<Resident> selectNonLocalPeople(Wrapper<Resident> wrapper, Page page) {
//        return residentMapper.getNonLocalResident(wrapper,page);
//    }
}
