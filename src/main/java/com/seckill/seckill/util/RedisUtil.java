package com.seckill.seckill.util;

public class RedisUtil {
    public static String getTokenKey(String tokenString) {
        return "token:" + tokenString;
    }

    public static String getUserKey(int userId) {
        return "user:" + userId;
    }
    
    public static String getCartKey(int userId) {
        return "cart:" + userId;
    }

    public static String getSeckillHistoryKey(int goodsId) {
        return "seckillHistory:" + goodsId;
    }
}
