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
public class Cart{
    private int id;
    private int userId;
    private int goodsId;
    private int amount;
    private Date createTime;
    private int status;
}
