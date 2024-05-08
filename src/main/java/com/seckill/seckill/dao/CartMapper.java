package com.seckill.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.CartGoods;

@Mapper
public interface CartMapper {

    List<CartGoods> selectCartGoods(int userId);

    int updateCartGoodsAmount(int userId, int goodsId, int amount);

    int insertCartGoods(int userId, int goodsId, int amount);

    int deleteCartGoods(int userId, int goodsId);

    int updateCartGoodsStatus(int userId, int goodsId);

    CartGoods selectCartGoodsByGoodsId(int userId, int goodsId);
}
