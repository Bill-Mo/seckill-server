package com.seckill.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Token;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.RedisUtil;
import com.seckill.seckill.vo.SeckillGoodsVo;

import java.util.List;
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("goodsList", goodsService.findSeckillGoods(0, 0));
        List<SeckillGoodsVo> goodsList = goodsService.findSeckillGoods(0, 0);
        for (SeckillGoodsVo goods : goodsList) {
            System.out.println(goods.toString());
        }
        return "goods/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        model.addAttribute("goods", goodsService.findSeckillGoodsById(id));
        return "goods/detail";
    }
}
