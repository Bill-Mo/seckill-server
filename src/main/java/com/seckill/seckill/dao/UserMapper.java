package com.seckill.seckill.dao;
import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.User;

@Mapper
public interface UserMapper {
    
    User selectByName(String name);

    int insertUser(User user);

    int updatePassword(int id, String password);

}
