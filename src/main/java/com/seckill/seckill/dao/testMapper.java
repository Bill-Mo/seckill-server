package com.seckill.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.seckill.seckill.entity.User;

@Mapper
public interface testMapper {

    // @Select("select id, username, password, create_time from user where id = #{id}")
    User selectById(int id);
}
