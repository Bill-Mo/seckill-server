package com.seckill.seckill.entity;

import java.util.Date;

import javafx.beans.binding.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Goods {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int sales;
    private int purchaseLimit;
    private String description;
    private String image;
    private String formattedSales;
    private boolean seckill = false;
}