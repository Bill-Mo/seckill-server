package com.seckill.seckill.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderGoods extends Goods{
    private int orderId;
    private int amount;
    private double price;
}
