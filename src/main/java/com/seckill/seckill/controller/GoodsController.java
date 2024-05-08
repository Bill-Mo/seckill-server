package com.seckill.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.Token;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.RedisUtil;

import java.util.List;
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        model.addAttribute("goods", goodsService.findGoodsById(id));
        System.out.println(goodsService.findGoodsById(id));
        return "goods/detail";
    }


}
