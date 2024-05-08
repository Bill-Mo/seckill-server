package com.seckill.seckill.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.CartGoods;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.CartService;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CartService cartService;
    
    @Autowired
    GoodsService goodsService;

    @LoginRequired
    @PostMapping("/add")
    @ResponseBody
    public RespBean addToCart(@RequestBody Map<String, Object> cartItemRequest) {
        Integer goodsId = Integer.parseInt((String) cartItemRequest.get("goodsId"));
        Integer amount = Integer.parseInt((String) cartItemRequest.get("amount"));

        User user = hostHolder.getUser();
        RespBean respBean = cartService.addToCart(user.getId(), goodsId, amount);
        System.out.println(respBean);
        return respBean;
    }

    @RequestMapping("/detail")
    @LoginRequired
    public String detail(Model model) {
        List<CartGoods> cart = cartService.getCart(hostHolder.getUser().getId());
        for (CartGoods goods : cart) {
        }
        model.addAttribute("cart", cart);
        return "/cart";
    }

    @RequestMapping("/delete/{id}")
    @LoginRequired
    public String delete(@PathVariable("id") int goodsId) {
        cartService.deleteFromCart(goodsId, hostHolder.getUser().getId());
        return "redirect:/cart/detail";
    }

    @RequestMapping("/update")
    @LoginRequired
    public String update(@RequestParam("id") int goodsId, @RequestParam("amount") int amount) {
        System.out.println("goodsId: " + goodsId + " amount: " + amount + " userId: " + hostHolder.getUser().getId());
        cartService.updateCartGoodsAmount(hostHolder.getUser().getId(), goodsId, amount);
        return "redirect:/cart/detail";
    }

    @RequestMapping("/select/{id}")
    @LoginRequired
    public String select(@PathVariable("id") int goodsId) {
        RespBean respBean = cartService.changeCartGoodsStatus(hostHolder.getUser().getId(), goodsId);
        if (respBean.getCode() != 200) {
            return "error";
        }
        return "redirect:/cart/detail";
    }
}
