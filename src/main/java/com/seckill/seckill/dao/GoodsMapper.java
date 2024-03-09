package com.seckill.seckill.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seckill.seckill.vo.SeckillGoodsVo;
@Mapper
public interface GoodsMapper {

    List<SeckillGoodsVo> selectSeckillGoods(int offset, int limit);

}
