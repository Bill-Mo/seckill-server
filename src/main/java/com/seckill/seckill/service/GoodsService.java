package com.seckill.seckill.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.GoodsMapper;
import com.seckill.seckill.vo.GoodsVo;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsVo> findGoods(int offset, int limit) {
        return goodsMapper.selectGoods(offset, limit); 
    }

    public GoodsVo findGoodsById(int id) {
        return goodsMapper.selectGoodsById(id);
    }

    public int findGoodsRows() {
        return goodsMapper.selectGoodsRows();
    }

    public int updateSeckillGoodsStock(int id, int stock) {
        return goodsMapper.updateSeckillGoodsStock(id, stock);
    }
}
