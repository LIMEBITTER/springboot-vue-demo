package com.example.springboot.controller.dto;


import lombok.Data;

/**
 * 登录包装对象
 */
@Data
public class UserDto {

    private int id;
    /**
     * 登录姓名
     */

    private String username;

    /**
     * 登录密码
     */
    private String password;

    private int isAdmin;
    private int rid;

}
