package com.seckill.seckill.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.GoodsMapper;
import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.SeckillGoods;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> findGoods(int offset, int limit) {
        return goodsMapper.selectGoods(offset, limit); 
    }

    public List<SeckillGoods> findSeckillGoods(List<Integer> goodsIds) {
        return goodsMapper.selectSeckillGoods(goodsIds);
    }

    public List<Goods> findGoodsById(int goodsId) {
        List<Integer> goodsIds = new ArrayList<>();
        goodsIds.add(goodsId);
        return goodsMapper.selectGoodsById(goodsIds);
    }

    public List<Goods> findGoodsById(List<Integer> goodsIds) {
        return goodsMapper.selectGoodsById(goodsIds);
    }

    public int findGoodsRows() {
        return goodsMapper.selectGoodsRows();
    }

    public int updateSeckillGoodsStock(int id, int stock) {
        return goodsMapper.updateSeckillGoodsStock(id, stock);
    }
}
