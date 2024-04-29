package com.seckill.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200, "SUCCESS"),

    ERROR(500, "SERVER ERROR"),

    LOGIN_ERROR(501, "Username or password is incorrect"),
    UPDATE_INFO_ERROR(502, "Update user information failed"),

    EMPTY_STOCK(510, "Out of stock"),
    REPEATE_ERROR(511, "You have already bought this item"),
    ORDER_FAIL(512, "Order failed"),

    CART_ERROR(520, "Cart error"),
    ;

    private final Integer code;
    private final String message;
}
