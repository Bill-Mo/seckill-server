package com.seckill.seckill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.Page;
import com.seckill.seckill.entity.SeckillGoods;
import com.seckill.seckill.service.GoodsService;

@Controller
public class HomeController {

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping("/index")
    public String list(Model model, Page page) {
        page.setRows(goodsService.findGoodsRows());
        page.setPath("/index");

        List<Goods> goodsList = goodsService.findGoods(page.getOffset(), page.getLimit());

        List<Integer> goodsIds = new ArrayList<>();
        for (Goods goods : goodsList) {
            goodsIds.add(goods.getId());
        }
        List<SeckillGoods> seckillGoodsList = goodsService.findSeckillGoods(goodsIds);

        for (int i = 0; i < goodsList.size(); i++) {
            for (int j = 0; j < seckillGoodsList.size(); j++) {
                if (goodsList.get(i).getId() == seckillGoodsList.get(j).getId()) {
                    goodsList.get(i).setSeckill(true);
                }
            }
        }

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("seckillGoodsList", seckillGoodsList);
        return "index";
    }
}
