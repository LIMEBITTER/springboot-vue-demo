package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 已经注释的方法均采用MP实现的方法代替,本接口只实现MP的BaseMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
