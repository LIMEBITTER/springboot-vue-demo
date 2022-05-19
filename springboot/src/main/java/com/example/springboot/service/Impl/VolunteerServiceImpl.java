package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.entity.Volunteer;
import com.example.springboot.mapper.VolunteerMapper;
import com.example.springboot.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 志愿者服务实现类
 */
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements VolunteerService {

    @Autowired
    private  VolunteerMapper volunteerMapper;
    /**
     * 查询所有志愿者信息
     * @return
     */
    @Override
    public List<VolunteerDto> getAllVolunteer() {
       return volunteerMapper.getAllVolunteerInfo();
    }

    @Override
    public IPage<VolunteerDto> getAllVolunteer(Page page) {
        return volunteerMapper.getAllVolunteer(page);
    }

    @Override
    public IPage<VolunteerDto> getAllVolunteerDto(Wrapper<VolunteerDto> wrapper, IPage page) {
        return volunteerMapper.getVolunteerDto(wrapper,page);
    }

    @Override
    public int save2(VolunteerDto volunteerDto) {
        return volunteerMapper.save2(volunteerDto);
    }

    @Override
    public boolean updateUserVId(int id, int rid) {
        return volunteerMapper.updateUserVId(id,rid);
    }

    @Override
    public List<VolunteerDto> getVolAddressChart() {
        return volunteerMapper.getVolAddressChart();
    }

    @Override
    public Integer selectVolById(Integer id) {
        return volunteerMapper.selectVolById(id);
    }

    @Override
    public boolean changeVStatus(Integer id) {
        return volunteerMapper.changeVStatus(id);
    }


}
