package com.seckill.seckill.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.UserService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.RedisUtil;
import com.seckill.seckill.util.SeckillUtil;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

@Controller
public class LoginController {

    @Autowired
    private HostHolder hostHolder;
        
        private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
        @Autowired
        private UserService userService;

        @RequestMapping("/toLogin")
        public String toLogin() {
            return "/login";
        }

        @RequestMapping("/login")
        public String login(String email, String password, boolean rememberMe, Model model, HttpServletRequest request, HttpServletResponse response) {
            if (email == null && password == null) {
                return "/login";
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
                return "/login";
            }
        }

        @LoginRequired
        @RequestMapping("/logout")
        public String logout(@CookieValue("token") String tokenString){
            RespBean respBean = userService.logout(tokenString);
            return "redirect:/toLogin";
        }
}
