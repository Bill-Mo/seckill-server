package com.seckill.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.UserMapper;
import com.seckill.seckill.entity.Token;
import com.seckill.seckill.entity.User;
import com.seckill.seckill.util.RedisUtil;
import com.seckill.seckill.util.SeckillUtil;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@Service
public class UserService {

    @Value("${server.servlet.context-path}")
    private String contextPath; 
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    public RespBean login(String username, String password, int expiredSec, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        User user = userMapper.selectByName(username);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!user.getPassword().equals(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // generate token
        Token token = new Token();
        token.setUser(user);
        token.setToken(SeckillUtil.generateUUID());
        token.setStatus(0);
        token.setExpired(new Date(System.currentTimeMillis() + (long) expiredSec * 1000));

        // set token in session and cookie
        // request.getSession().setAttribute(token.getToken(), user);
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setPath(contextPath);
        cookie.setMaxAge(expiredSec);
        response.addCookie(cookie);

        // set token in redis
        String redisKey = RedisUtil.getTokenKey(token.getToken());
        System.out.println(token.getToken());
        redisTemplate.opsForValue().set(redisKey, token);

        return RespBean.success();
    }

    public Token findToken(String tokenString) {
        return (Token) redisTemplate.opsForValue().get(RedisUtil.getTokenKey(tokenString));
    }
}
