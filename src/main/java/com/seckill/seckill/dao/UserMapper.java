package com.seckill.seckill.dao;
import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.User;

@Mapper
public interface UserMapper {
    
    User selectByEmail(String email);

    int insertUser(User user);

    int updatePassword(int userId, String password);

    int updateAddress(int userId, String address);
}
