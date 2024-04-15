package com.seckill.seckill.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class SeckillGoods extends Goods{
    private double seckillPrice;
    private int seckillStock;
    private Date startTime;
    private Date endTime;
}
