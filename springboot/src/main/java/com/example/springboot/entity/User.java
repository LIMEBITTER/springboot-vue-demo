package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user")
public class User {

    /**
     * 管理员id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private int id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 登录密码
     */
    @JsonIgnore //防止传输泄露密码
    private String password;

    private int isAdmin;
    private int rid;


}
