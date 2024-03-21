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
    private String username;
    private int goodsId;
    private double price;
    private int amount;
    private String address;
    private Date paymentTime;
    private Date createTime;
    private int status;
    private int paymentMethod;
}
