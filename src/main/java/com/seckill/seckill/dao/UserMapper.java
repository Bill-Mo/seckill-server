package com.seckill.seckill.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.seckill.seckill.entity.User;

@Mapper
public interface UserMapper {
    
    @Select("select id, username, password, create_time from user where id = #{id}")
    User a(int id);

    User selectByName(String name);

    int insertUser(User user);

    int updatePassword(int id, String password);

}
