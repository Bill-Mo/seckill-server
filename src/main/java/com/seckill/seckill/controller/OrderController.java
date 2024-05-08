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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<?> checkout() {
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
    @PostMapping("/buyNow")
    @ResponseBody
    @Transactional
    public RespBean checkout(@RequestBody Map<String, Object> orderRequest) {
        Integer goodsId = Integer.parseInt((String) orderRequest.get("goodsId"));
        Integer amount = Integer.parseInt((String) orderRequest.get("amount"));

        User user = hostHolder.getUser();
        RespBean respBean = orderService.checkout(user.getId(), user.getAddress(), goodsId, amount);
        return respBean;
    }


    @LoginRequired
    @RequestMapping("/{orderId}")
    public String detail(@PathVariable("orderId") int orderId, Model model) {
        User user = hostHolder.getUser();
        Order order = orderService.getOrder(user.getId(), orderId);
        List<OrderGoods> orderGoodsList = orderService.getOrderGoods(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderGoodsList", orderGoodsList);
        return "order/detail";
    }

    @LoginRequired
    @RequestMapping("/placeOrder")
    @Transactional
    public ResponseEntity<?> placeOrder(@RequestParam("orderId") int orderId, @RequestParam("paymentMethod") String paymentMethod, Model model) {
        User user = hostHolder.getUser();
        RespBean respBean = orderService.placeOrder(paymentMethod, user.getId(), orderId);
        if (respBean.getCode() != 200) {
        }
        double totalPrice = (double) respBean.getObj();
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalPrice", totalPrice);

        if (respBean.getCode() == 200) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("orderId", orderId);
            response.put("totalPrice", totalPrice);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", respBean.getCode());
            response.put("message", respBean.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @LoginRequired
    @RequestMapping("/success")
    public String success(Model model, @RequestParam("orderId") int orderId, @RequestParam("totalPrice") double totalPrice) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalPrice", totalPrice);
        return "order/success";
    }

    @LoginRequired
    @RequestMapping("/update/address")
    @ResponseBody
    public RespBean updateOrderAddress(@RequestParam("orderId") int orderId, @RequestParam("address") String address) {
        RespBean respBean = orderService.updateOrderAddress(orderId, address);
        return respBean;
    }
}
