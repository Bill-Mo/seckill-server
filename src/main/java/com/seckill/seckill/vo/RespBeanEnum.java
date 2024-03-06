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
    ;

    private final Integer code;
    private final String message;
}
