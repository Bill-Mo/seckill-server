package com.seckill.seckill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.CartMapper;
import com.seckill.seckill.entity.Cart;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    
    public void addToCart(int goodsId, int amount, int userId) {
        List<Cart> getCart = getCart(userId);
        for (Cart cart : getCart) {
            if (cart.getGoodsId() == goodsId) {
                cartMapper.updateCart(userId, goodsId, amount);
                return;
            }
        }
        cartMapper.insertCart(userId, goodsId, amount);
    }

    public List<Cart> getCart(int userId) {
        return cartMapper.selectCart(userId);
    }
}
