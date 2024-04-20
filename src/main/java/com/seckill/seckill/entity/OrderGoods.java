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

    public OrderGoods(CartGoods cartGoods, int orderId) {
        super(cartGoods.getId(), cartGoods.getName(), cartGoods.getPrice(), cartGoods.getStock(), cartGoods.getDescription(), cartGoods.getImage(), cartGoods.isSeckill());
        
        this.orderId = orderId;
        this.amount = cartGoods.getAmount();
    }
}
