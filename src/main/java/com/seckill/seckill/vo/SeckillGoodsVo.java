package com.seckill.seckill.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class SeckillGoodsVo {
    private int id;
    private String name;
    private double price;
    private double seckillPrice;
    private int seckillStock;
    private String description;
    private Date startTime;
    private Date endTime;
    private int status;
}