package com.seckill.seckill.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.SeckillGoods;
@Mapper
public interface GoodsMapper {

    List<Goods> selectGoods(int offset, int limit);

    List<SeckillGoods> selectSeckillGoods(List<Integer> goodsIds);

    List<Goods> selectGoodsById(List<Integer> goodsIds);

    int selectGoodsRows();

    List<Goods> selectGoodsByCart(int userId);

    int updateSeckillGoodsStock(int goodsId, int stock);
}
