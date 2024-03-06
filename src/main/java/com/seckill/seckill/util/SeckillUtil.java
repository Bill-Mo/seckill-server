package com.seckill.seckill.util;

import java.util.UUID;

public class SeckillUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    
}
