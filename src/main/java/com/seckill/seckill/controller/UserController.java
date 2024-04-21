package com.seckill.seckill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Order;
import com.seckill.seckill.entity.OrderGoods;
import com.seckill.seckill.entity.Page;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.OrderService;
import com.seckill.seckill.util.HostHolder;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping("/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/orders")
    public String orders(Model model, Page page, @RequestParam(name = "mode", defaultValue = "-1") int mode) {
        User user = hostHolder.getUser();
        page.setRows(orderService.countUserOrders(user.getId(), mode));
        page.setPath("/user/orders");

        List<Order> orders = orderService.getUserOrders(user.getId(), page.getOffset(), page.getLimit(), mode);
        for (Order order : orders) {
            List<OrderGoods> orderGoodsList = orderService.getOrderGoods(order.getId());
            order.setOrderGoods(orderGoodsList);
        }
        
        model.addAttribute("orders", orders);
        return "order/list";
    }
}
