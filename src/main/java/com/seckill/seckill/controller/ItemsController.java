package com.seckill.seckill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.User;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @RequestMapping("/list")
    @LoginRequired
    public String list(HttpSession session, Model model, @CookieValue("token") String token) {
        User user = (User) session.getAttribute(token);
        model.addAttribute("user", user);
        return "items/list";
    }
}
