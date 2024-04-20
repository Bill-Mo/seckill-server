package com.seckill.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.Order;
import com.seckill.seckill.entity.OrderGoods;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    int insertOrderGoods(int orderId, int goodsId, int amount, double price);

    List<OrderGoods> selectOrderGoods(int orderId);
    
    List<Order> selectOrders(int userId, int offset, int limit);

    Order selectOrderById(int userId, int orderId);
}
