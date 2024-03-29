package com.seckill.seckill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.GoodsMapper;
import com.seckill.seckill.vo.SeckillGoodsVo;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<SeckillGoodsVo> findSeckillGoods(int offset, int limit) {
        return goodsMapper.selectSeckillGoods(offset, limit); 
    }

    public SeckillGoodsVo findSeckillGoodsById(int id) {
        return goodsMapper.selectSeckillGoodsById(id);
    }

    public int updateSeckillGoodsStock(int id, int stock) {
        return goodsMapper.updateSeckillGoodsStock(id, stock);
    }
}
