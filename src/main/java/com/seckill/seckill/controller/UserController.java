package com.seckill.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;

@Controller
@RequestMapping("/user")
public class UserController {

    @LoginRequired
    @RequestMapping("profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("orders")
    public String orders() {
        return "order/list";
    }
}
