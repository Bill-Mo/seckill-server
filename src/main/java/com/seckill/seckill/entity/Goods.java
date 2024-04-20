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
    private String description;
    private String image;
    private boolean seckill = false;
}