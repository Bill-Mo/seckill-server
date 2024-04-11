package com.seckill.seckill.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User{
    private int id;
    private String email;
    private String password;
    private String username;
    private Date createTime;
    private String address;
    
}

