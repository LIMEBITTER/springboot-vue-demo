package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.controller.dto.HealthDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Health;
import com.example.springboot.mapper.HealthMapper;
import com.example.springboot.mapper.VolunteerMapper;
import com.example.springboot.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 健康服务实现类
 */
@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {

    @Autowired
    private HealthMapper healthMapper;

    @Override
    public List<HealthDto> getAllHealth() {
        return healthMapper.getAllHealth();
    }

    /**
     * 查询所有居民健康信息
     *
     * @return
     */
    @Override
    public IPage<HealthDto> getAllHealthInfo(Page page) {
        return healthMapper.getAllHealthInfo(page);
    }

    @Override
    public IPage<HealthDto> getAllHealthDto(Wrapper<HealthDto> wrapper,IPage page) {
        return healthMapper.getHealthDto(wrapper,page);
    }

    @Override
    public boolean save2(HealthDto healthDto) {
        return healthMapper.save2(healthDto);
    }

    @Override
    public boolean updateHealthId(int id,int rid) {
        return healthMapper.updateHealthId(id,rid);
    }

    @Override
    public boolean changeHStatus(Integer id) {
        return healthMapper.changeHStatus(id);
    }

    @Override
    public List<HealthDto> tempCountChart() {
        return healthMapper.tempCountChart();
    }

    @Override
    public List<HealthDto> getHealthSituation() {
        return healthMapper.getHealthSituation();
    }

    @Override
    public Integer selectHealthById(Integer id) {
        return healthMapper.selectHealthById(id);
    }

//    @Override
//    public boolean save1(Health health) {
//        return healthMapper.saveHealth(health);
//    }

}
