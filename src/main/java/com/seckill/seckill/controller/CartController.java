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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping("")
    @ResponseBody
    public RespBean addToCart(@RequestBody Map<String, Object> cartItemRequest) {
        Integer goodsId = Integer.parseInt((String) cartItemRequest.get("goodsId"));
        Integer amount = Integer.parseInt((String) cartItemRequest.get("amount"));

        User user = hostHolder.getUser();
        RespBean respBean = cartService.addToCart(user.getId(), goodsId, amount);
        System.out.println(respBean);
        return respBean;
    }

    @GetMapping("")
    @LoginRequired
    public String detail(Model model) {
        List<CartGoods> cart = cartService.getCart(hostHolder.getUser().getId());
        model.addAttribute("cart", cart);
        return "/cart";
    }

    @PostMapping("/{id}")
    @LoginRequired
    @ResponseBody
    public RespBean update(@PathVariable("id") int goodsId, @RequestBody Map<String, Object> cartItemRequest) {
        RespBean respBean;
        if (cartItemRequest == null || cartItemRequest.get("amount") == null) {
            respBean = cartService.changeCartGoodsStatus(hostHolder.getUser().getId(), goodsId);
        } else {
            int amount = (int) cartItemRequest.get("amount");
            respBean = cartService.updateCartGoodsAmount(hostHolder.getUser().getId(), goodsId, amount);
        }

        return respBean;
    }
    
    @DeleteMapping("/{id}")
    @LoginRequired
    @ResponseBody
    public RespBean delete(@PathVariable("id") @RequestBody String goodsId) {
        // Integer goodsId = Integer.parseInt((String) cartItemRequest.get("goodsId"));
        int id = Integer.parseInt(goodsId);
        RespBean respBean = cartService.deleteFromCart(id, hostHolder.getUser().getId());
        return respBean;
    }

}
