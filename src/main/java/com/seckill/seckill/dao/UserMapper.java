package com.seckill.seckill.dao;
import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.User;

@Mapper
public interface UserMapper {
    
    User selectUserByEmail(String email);

    User selectUserById(int userId);

    int insertUser(User user);

    int updateUsername(String username, int userId);

    int updatePassword(String password, int userId);

    int updateAddress(String address, int userId);
}
