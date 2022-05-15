package com.example.springboot.service.Impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.CustomException;
import com.example.springboot.mapper.UserMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 管理员服务实现类
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User>{


    private static final Log L0G = Log.get();

    /**
     * 新增或删除用户
     * @param user
     * @return
     */
    public boolean saveUser(User user) {
        if(user.getId() == 0){
            return save(user); //user没有id,则表示是新增用户
        }else{
            return updateById(user);//有id则为更新用户信息
        }

    }


    /**
     * 管理员登录
     * @param userDto
     * @return
     */
    public UserDto login(UserDto userDto) {
        User one = getUserInfo(userDto);
        System.out.println("userService=====login==User"+one);
//        System.out.println("userService=====login==userDto"+userDto);
        if (one != null) {
            BeanUtils.copyProperties(one, userDto);
            System.out.println("userService=====login==userDto"+userDto);

            return userDto;
        } else {
            throw new CustomException(Constants.CODE_666,"用户或密码错误");
        }
    }

    /**
     * 管理员注册
     * @param userDto
     * @return
     */
    public User register(UserDto userDto) {
        User one = getUserInfo(userDto);
        if (one == null) {
            one = new User();
            one.setUsername(userDto.getUsername());
            one.setPassword(userDto.getPassword());
            save(one);//存入数据库
        } else {
            throw new CustomException(Constants.CODE_668, "用户已存在");
        }
        return one;
    }


    /**
     * 获取登录信息包装
     * @param userDto
     * @return
     */
    private User getUserInfo(UserDto userDto){
        //匹配userDto姓名和密码是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDto.getUsername());
        wrapper.eq("password",userDto.getPassword());
        //校验
        User one;
        try {
            one=getOne(wrapper);//从数据库查询信息
            System.out.println("UserService=======getUserInfo"+one);
        } catch (Exception e) {
            L0G.error(e);//抛出错误日志
            throw new CustomException(Constants.CODE_667,"用户已经存在");
        }
        //证明确实不存在这个用户，返回它的值应该是null
        return one;
    }


}
