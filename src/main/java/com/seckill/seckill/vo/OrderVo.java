package com.seckill.seckill.vo;

import java.util.Date;
import java.util.List;

import com.seckill.seckill.entity.Goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderVo {
    private int id;
    private List<Goods> goodsList;
    private double totalPrice;
    private String username;
    private String address;
    private Date createTime;
    private String paymentMethod;
    private int status;
}
