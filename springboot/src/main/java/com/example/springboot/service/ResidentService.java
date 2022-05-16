package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.ResidentDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;

import java.util.List;

/**
 * 居民信息服务
 */
public interface ResidentService extends IService<Resident> {

    //返回查询信息
    IPage<Resident> selectAllLocal(Page page);
    IPage<Resident> selectAllNonLocal(Page page);
    List<Resident> getAllPerson();

    Resident selectResidentById(int id);


   //两个分页信息
    IPage<Resident> selectLocalPeople(Wrapper<Resident> wrapper,IPage page);

    int save2(ResidentDto residentDto);

    boolean updateUserRId(ResidentDto residentDto);
//    IPage<Resident> selectNonLocalPeople(Wrapper<Resident> wrapper, Page page);
}
