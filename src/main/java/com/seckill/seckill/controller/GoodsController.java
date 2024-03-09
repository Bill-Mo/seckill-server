package com.seckill.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Token;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.RedisUtil;

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
    @LoginRequired
    public String list(Model model, @CookieValue("token") String token) {
        model.addAttribute("user", hostHolder.getUser());
        model.addAttribute("goodsList", goodsService.findSeckillGoods(0, 0));
        System.out.println(goodsService.findSeckillGoods(0, 0));
        return "goods/list";
    }
}
