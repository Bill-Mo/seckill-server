package com.seckill.seckill.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Cart;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.CartService;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.util.HostHolder;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CartService cartService;
    
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/add/{id}")
    @LoginRequired
    public String addToCart(@PathVariable("id") int goodsId, @CookieValue("amount") int amount){
        User user = hostHolder.getUser();
        cartService.addToCart(goodsId, amount, user.getId());
        return "redirect:/cart";
    }

    @RequestMapping("/detail")
    @LoginRequired
    public String detail(Model model) {
        List<Cart> cartList = cartService.getCart(hostHolder.getUser().getId());
        List<Integer> goodsIdList = cartList.stream().map(Cart::getGoodsId).collect(Collectors.toList());
        
        model.addAttribute("cartList", cartList);
        return "/cart";
    }
}
