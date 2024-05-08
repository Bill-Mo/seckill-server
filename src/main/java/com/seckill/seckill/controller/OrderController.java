package com.seckill.seckill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.seckill.annotation.LoginRequired;
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

    @RequestMapping("")
    @LoginRequired
    @Transactional
    @ResponseBody
    public RespBean checkout() {
        RespBean respBean = orderService.checkout();
        return respBean;
    }

    @LoginRequired
    @PostMapping("/buyNow")
    @ResponseBody
    @Transactional
    public RespBean checkout(@RequestBody Map<String, Object> orderRequest) {
        Integer goodsId = Integer.parseInt((String) orderRequest.get("goodsId"));
        Integer amount = Integer.parseInt((String) orderRequest.get("amount"));
        RespBean respBean = orderService.checkout(goodsId, amount);
        return respBean;
    }


    @LoginRequired
    @GetMapping("/{orderId}")
    public String detail(@PathVariable("orderId") int orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        List<OrderGoods> orderGoodsList = orderService.getOrderGoods(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderGoodsList", orderGoodsList);
        return "order/detail";
    }

    @LoginRequired
    @PostMapping("/placeOrder")
    @Transactional
    @ResponseBody
    public RespBean placeOrder(@RequestBody Map<String, Object> orderRequest) {
        String paymentMethod = (String) orderRequest.get("paymentMethod");
        Integer orderId = Integer.parseInt((String) orderRequest.get("orderId"));
        RespBean respBean = orderService.placeOrder(paymentMethod, orderId);
        return respBean;
    }

    @LoginRequired
    @GetMapping("/success")
    public String success(Model model, @RequestParam("orderId") int orderId, @RequestParam("totalPrice") double totalPrice) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalPrice", totalPrice);
        return "order/success";
    }

    @LoginRequired
    @PostMapping("/address")
    @ResponseBody
    public RespBean updateOrderAddress(@RequestBody Map<String, Object> orderRequest) {
        Integer orderId = Integer.parseInt((String) orderRequest.get("orderId"));
        String address = (String) orderRequest.get("address");
        RespBean respBean = orderService.updateOrderAddress(orderId, address);
        return respBean;
    }
}
