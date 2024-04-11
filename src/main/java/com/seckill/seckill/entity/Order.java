package com.seckill.seckill.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
    private int id;
    private int userId;
    private double totalPrice;
    private String address;
    private int isSeckill;
    private Date paymentTime;
    private Date createTime;
    private int status;
    private int paymentMethod;
}