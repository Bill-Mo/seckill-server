package com.seckill.seckill.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.UserService;
import com.seckill.seckill.vo.RespBean;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String email, String password, boolean rememberMe, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        if (email == null && password == null) {
            return "login";
        }

        int expiredSec;
        if (!rememberMe) {
            expiredSec = 3600 * 24;
        } else {
            expiredSec = 3600 * 24 * 30;
        }
        RespBean respBean = userService.login(email, password, expiredSec, response, request);

        System.out.println(respBean.getCode());
        if (respBean.getCode() == 200) {

            return "redirect:/index";
        } else {
            model.addAttribute("error", respBean.getMessage());
            return "login";
        }
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(String username, String email, String password, String address, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setCreateTime(new Date());

        RespBean respBean = userService.register(user);
        if (respBean.getCode() == 200) {
            return "redirect:/toLogin";
        } else {
            model.addAttribute("error", respBean.getMessage());
            return "register";
        }
    }

    @LoginRequired
    @RequestMapping("/logout")
    public String logout(@CookieValue("token") String tokenString) {
        userService.logout(tokenString);
        return "redirect:/toLogin";
    }
}
