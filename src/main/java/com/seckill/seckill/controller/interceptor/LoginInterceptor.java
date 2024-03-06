package com.seckill.seckill.controller.interceptor;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.seckill.seckill.annotation.LoginRequired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            if (loginRequired != null) {

                Cookie[] cookies = request.getCookies();
                boolean tokenExist = false;
                String token = null;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            tokenExist = true;
                            token = cookie.getValue();
                            break;
                        }
                    }
                }
                System.out.println(tokenExist + " " + request.getSession().getAttribute(token));
                if (!tokenExist || request.getSession().getAttribute(token) == null) {
                    response.sendRedirect(request.getContextPath() + "/login");
                    return false;
                }
            }
        }

        return true;
    }
}
