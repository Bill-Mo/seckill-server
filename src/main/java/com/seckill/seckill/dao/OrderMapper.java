package com.seckill.seckill.dao;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.Order;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);


    Order selectOrder(String username, int goodsId);
}
