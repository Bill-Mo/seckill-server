package com.seckill.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.Cart;

@Mapper
public interface CartMapper {

    List<Cart> selectCart(int userId);

    int updateCart(int userId, int goodsId, int amount);

    int insertCart(int userId, int goodsId, int amount);

}
