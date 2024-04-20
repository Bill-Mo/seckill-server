package com.seckill.seckill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.CartGoods;
import com.seckill.seckill.entity.Order;
import com.seckill.seckill.entity.OrderGoods;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.CartService;
import com.seckill.seckill.service.OrderService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.vo.RespBean;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private CartService cartService;

    @RequestMapping("/checkout")
    @LoginRequired
    @Transactional
    public ResponseEntity<?> checkout(Model model) {
        User user = hostHolder.getUser();
        List<CartGoods> cart = cartService.getCart(user.getId());
        RespBean respBean = orderService.checkout(user.getId(), user.getAddress(), cart);
        
        if (respBean.getCode() == 200) {
            int orderId = (int) respBean.getObj();
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("orderId", orderId);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", respBean.getCode());
            response.put("message", respBean.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @LoginRequired
    @RequestMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") int orderId, Model model) {
        User user = hostHolder.getUser();
        Order order = orderService.getOrder(user.getId(), orderId);
        List<OrderGoods> orderGoodsList = orderService.getOrderGoods(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderGoodsList", orderGoodsList);
        return "order/detail";
    }
}
