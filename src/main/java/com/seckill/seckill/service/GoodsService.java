package com.seckill.seckill.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.GoodsMapper;
import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.SeckillGoods;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

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

    public Goods findGoodsById(int goodsId) {
        List<Integer> goodsIds = new ArrayList<>();
        goodsIds.add(goodsId);
        return goodsMapper.selectGoodsById(goodsIds).get(0);
    }

    public List<Goods> findGoodsById(List<Integer> goodsIds) {
        return goodsMapper.selectGoodsById(goodsIds);
    }

    public int findGoodsRows() {
        return goodsMapper.selectGoodsRows();
    }

    public RespBean updateGoodsStock(int goodsId, int stock) {
        Goods goods = findGoodsById(goodsId);
        if (goods == null || goods.getStock() + stock < 0) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }

        int result = goodsMapper.updateGoodsStock(goodsId, stock);
        if (result == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.ORDER_FAIL);
        }
    }

    public int updateSeckillGoodsStock(int id, int stock) {
        return goodsMapper.updateSeckillGoodsStock(id, stock);
    }
}
