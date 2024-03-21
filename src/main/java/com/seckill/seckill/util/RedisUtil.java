package com.seckill.seckill.util;

public class RedisUtil {
    public static String getTokenKey(String token) {
        return "token:" + token;
    }

    public static String getSeckillHistoryKey(int goodsId) {
        return "seckillHistory:" + goodsId;
    }
}
