package com.seckill.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Goods {
    private int id;
    private String name;
    private double price;
    private int amount;
}