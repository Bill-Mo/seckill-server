package com.seckill.seckill.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.vo.GoodsVo;
@Mapper
public interface GoodsMapper {

    List<GoodsVo> selectGoods(int offset, int limit);

    GoodsVo selectGoodsById(int id);

    int selectGoodsRows();

    int updateSeckillGoodsStock(int goodsId, int stock);
}
