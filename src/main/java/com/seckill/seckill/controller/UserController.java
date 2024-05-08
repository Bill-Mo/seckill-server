package com.seckill.seckill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.Order;
import com.seckill.seckill.entity.OrderGoods;
import com.seckill.seckill.entity.Page;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.OrderService;
import com.seckill.seckill.service.UserService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.SeckillUtil;
import com.seckill.seckill.vo.RespBean;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @GetMapping("/profile")
    public String profile(Model model) {
        User user = hostHolder.getUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @LoginRequired
    @GetMapping("/orders")
    public String orders(Model model, Page page, @RequestParam(name = "mode", defaultValue = "-1") int mode) {
        User user = hostHolder.getUser();
        page.setRows(orderService.countUserOrders(user.getId(), mode));
        page.setPath("/user/orders?mode=" + mode);

        List<Order> orders = orderService.getUserOrders(user.getId(), page.getOffset(), page.getLimit(), mode);
        for (Order order : orders) {
            List<OrderGoods> orderGoodsList = orderService.getOrderGoods(order.getId());
            order.setOrderGoods(orderGoodsList);
        }
        
        model.addAttribute("orders", orders);
        model.addAttribute("mode", mode);
        return "order/list";
    }

    @LoginRequired
    @PostMapping("/update/{field}")
    public String updateUserUsername(@PathVariable("field") String field, @RequestParam("value") String value) {
        User user = hostHolder.getUser();
        if (field == "username") {
            user.setUsername(value);
        } else if (field == "password") {
            user.setPassword(value);
        } else if (field == "address") {
            user.setAddress(value);
        }
        RespBean respBean = userService.updateUser(user);
        if (respBean.getCode() == 200) {
            return "redirect:/user/profile";
        } 
        return "/error";
    }
}