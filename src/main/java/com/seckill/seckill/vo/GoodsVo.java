package com.seckill.seckill.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class GoodsVo {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private String image;
    private double seckillPrice;
    private int seckillStock;
    private Date startTime;
    private Date endTime;
}