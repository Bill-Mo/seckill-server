package com.seckill.seckill.dao;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.Order;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    int insertSeckillOrder(String username, int orderId);

    Order selectOrder(String username, int goodsId);
}
