package com.seckill.seckill.controller.interceptor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seckill.seckill.entity.Token;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.service.UserService;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.SeckillUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                String tokenString = SeckillUtil.getValue(request, "token");
                if (tokenString != null) {
                    // Get token
                    Token token = userService.findToken(tokenString);
                    // Check validity of token
                    if (token != null && token.getStatus() == 0 && token.getExpired().after(new Date())) {
                        System.out.println("Token will expire in " + (token.getExpired().getTime() - new Date().getTime()) / 1000 + " seconds");
                        // Search for user
                        User user = token.getUser();
                        // Save user status
                        hostHolder.setUser(user);
                    } 
                }
                return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
                hostHolder.clear();
	}
}
