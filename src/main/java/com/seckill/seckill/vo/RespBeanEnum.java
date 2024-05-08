package com.seckill.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200, "SUCCESS"),

    LOGIN_REQUIRE(401, "Please login first"),

    ERROR(500, "SERVER ERROR"),

    LOGIN_ERROR(501, "Username or password is incorrect"),
    UPDATE_INFO_ERROR(502, "Update user information failed"),
    INVALID_INFO(503, "Invalid information"),
    DUPLICATE_INFO(504, "Please Enter a different value"),
    USER_NOT_FOUND(505, "User not found"),

    EMPTY_STOCK(510, "Out of stock"),
    REPEATE_ERROR(511, "You have already bought this item"),
    GOODS_NOT_FOUND(512, "Goods not found"),
    
    CART_ERROR(520, "Cart error"),
    INVALID_CART(521, "Invalid cart"),
    UPDATE_CART_ERROR(522, "Update cart failed"),

    INVALID_USER(530, "Invalid user information"),
    ORDER_FAIL(531, "Order failed"),
    UPDATE_ORDER_ERROR(532, "Update order failed"),
    ;

    private final Integer code;
    private final String message;
}
