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
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private CartService cartService;
    
    public RespBean login(String email, String password, int expiredSec, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        
        User user = userMapper.selectUserByEmail(email);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!user.getPassword().equals(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // generate token
        Token token = new Token();
        token.setUserId(user.getId());
        token.setTokenString(SeckillUtil.generateUUID());
        token.setStatus(0);
        token.setExpired(new Date(System.currentTimeMillis() + (long) expiredSec * 1000));

        // set token in session and cookie
        // request.getSession().setAttribute(token.getToken(), user);
        Cookie cookie = new Cookie("token", token.getTokenString());
        cookie.setPath(contextPath);
        cookie.setMaxAge(expiredSec);
        response.addCookie(cookie);

        // set token in redis
        String redisKey = RedisUtil.getTokenKey(token.getTokenString());
        System.out.println(user.getId());
        redisTemplate.opsForValue().set(redisKey, token);

        return RespBean.success();
    }

    public Token findToken(String tokenString) {
        return (Token) redisTemplate.opsForValue().get(RedisUtil.getTokenKey(tokenString));
    }

    public RespBean logout(String tokenString) {
        String redisKey = RedisUtil.getTokenKey(tokenString);
        Token loginTicket = (Token) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey, loginTicket);
        return RespBean.success();
    }

    public RespBean updateUser(User user, String field, String value, String tokenString) {
        int result = 0;
        if (field.equals("username")) {
            // validate username
            if (StringUtils.isBlank(value)) {
                return RespBean.error(RespBeanEnum.INVALID_INFO);
            }
            if (user.getUsername().equals(value)) {
                return RespBean.error(RespBeanEnum.DUPLICATE_INFO);
            }

            result = userMapper.updateUsername(value, user.getId());
        } else if (field.equals("password")) {
            // validate password
            if (StringUtils.isBlank(value)) {
                return RespBean.error(RespBeanEnum.INVALID_INFO);
            }
            if (user.getPassword().equals(value)) {
                return RespBean.error(RespBeanEnum.DUPLICATE_INFO);
            }

            result = userMapper.updatePassword(value, user.getId());
            logout(tokenString);
        } else if (field.equals("address")) {
            // validate address
            if (StringUtils.isBlank(value)) {
                return RespBean.error(RespBeanEnum.INVALID_INFO);
            }
            if (user.getAddress().equals(value)) {
                return RespBean.error(RespBeanEnum.DUPLICATE_INFO);
            }

            result = userMapper.updateAddress(value, user.getId());
        }
        clearCache(user.getId());

        if (result == 1) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.UPDATE_INFO_ERROR);
    }

    public User findUserById(int userId) {
        User user = getCache(userId);
        if (user == null) {
            user = initCache(userId);
        }
        return user;
    }

    // Get value from cache
    private User getCache(int userId) {
        String redisKey = RedisUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    // Initialize cache
    private User initCache(int userId) {
        User user = userMapper.selectUserById(userId);
        String redisKey = RedisUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS);
        return user;
    }

    // Clear cache when data updated
    private void clearCache(int userId) {
        String redisKey = RedisUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }
}
