package com.seckill.seckill.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.CartGoods;
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

    @RequestMapping("/add")
    @LoginRequired
    public String addToCart(@RequestParam("id") int goodsId, @RequestParam("amount") int amount){
        User user = hostHolder.getUser();
        cartService.addToCart(goodsId, amount, user.getId());
        return "redirect:/cart/detail";
    }

    @RequestMapping("/detail")
    @LoginRequired
    public String detail(Model model) {
        List<CartGoods> cart = cartService.getCart(hostHolder.getUser().getId());
        
        model.addAttribute("cart", cart);
        return "/cart";
    }

    @RequestMapping("/delete/{id}")
    @LoginRequired
    public String delete(@PathVariable("id") int goodsId) {
        cartService.deleteFromCart(goodsId, hostHolder.getUser().getId());
        return "redirect:/cart/detail";
    }
}
