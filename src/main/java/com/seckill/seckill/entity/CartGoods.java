package com.seckill.seckill.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartGoods extends Goods{
    private int userId;
    private int amount;
    private Date createTime;
    private int status;
}
