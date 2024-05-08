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
        List<Goods> goodsList = goodsMapper.selectGoods(offset, limit); 
        for (Goods goods : goodsList) {
            goods.setFormattedSales(formatSales(goods.getSales()));
        }
        return goodsList;
    }

    public List<SeckillGoods> findSeckillGoods(List<Integer> goodsIds) {
        return goodsMapper.selectSeckillGoods(goodsIds);
    }

    public Goods findGoodsById(int goodsId) {
        List<Integer> goodsIds = new ArrayList<>();
        goodsIds.add(goodsId);
        return findGoodsById(goodsIds).get(0);
    }

    public List<Goods> findGoodsById(List<Integer> goodsIds) {
        List<Goods> goodsList = goodsMapper.selectGoodsById(goodsIds);
        for (Goods goods : goodsList) {
            goods.setFormattedSales(formatSales(goods.getSales()));
        }
        return goodsList;
    }

    public int findGoodsRows() {
        return goodsMapper.selectGoodsRows();
    }

    public RespBean updateGoodsStock(int goodsId, int stock) {
        Goods goods = findGoodsById(goodsId);
        if (goods == null) {
            return RespBean.error(RespBeanEnum.GOODS_NOT_FOUND);
        }
        if (goods.getStock() + stock < 0) {
            return RespBean.error(RespBeanEnum.OUT_OF_STOCK);
        }

        int result = goodsMapper.updateGoodsStock(goodsId, stock);
        if (result == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.ORDER_FAIL);
        }
    }

    public RespBean updateGoodsSales(int goodsId, int sales) {
        Goods goods = findGoodsById(goodsId);
        if (goods == null) {
            return RespBean.error(RespBeanEnum.GOODS_NOT_FOUND);
        }
        if (goods.getSales() + sales < 0) {
            return RespBean.error(RespBeanEnum.INVALID_INFO);
        }
        int result = goodsMapper.updateGoodsSales(goodsId, sales);
        if (result == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.ORDER_FAIL);
        }
    }

    public int updateSeckillGoodsStock(int id, int stock) {
        return goodsMapper.updateSeckillGoodsStock(id, stock);
    }

    
    public String formatSales(int sales) {
        if (9 < sales && sales < 99) {
            return sales / 10 + "0+";
        } else if (99 < sales && sales < 999) {
            return sales / 100 + "00+";
        } else if (999 < sales && sales < 9999) {
            return sales / 1000 + "k+";
        } else if (9999 < sales && sales < 99999) {
            return sales / 10000 + "0k+";
        } else if (99999 < sales && sales < 999999) {
            return sales / 100000 + "00k+";
        } else if (999999 < sales && sales < 9999999) {
            return sales / 1000000 + "m+";
        } else if (9999999 < sales && sales < 99999999) {
            return sales / 10000000 + "0m+";
        } else if (99999999 < sales && sales < 999999999) {
            return sales / 100000000 + "00m+";
        } else {
            return sales + "";
        }
    }
}
