package com.seckill.seckill.controller.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.seckill.seckill.annotation.LoginRequired;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.SeckillUtil;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(10)
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            
            if (loginRequired != null) {
                System.out.println("Login required");
                if (hostHolder.getUser() == null) {
                    String xRequestedWith = request.getHeader("x-requested-with");
                    if ("XMLHttpRequest".equals(xRequestedWith)) {
                        response.setContentType("application/json");
                        PrintWriter writer = response.getWriter();
                        writer.write(RespBean.error(RespBeanEnum.LOGIN_REQUIRE).toJsonString());
                    } else {
                        response.sendRedirect(request.getContextPath() + "/login");
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
