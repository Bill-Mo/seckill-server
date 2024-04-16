package com.seckill.seckill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.CartMapper;
import com.seckill.seckill.entity.CartGoods;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    
    public void addToCart(int goodsId, int amount, int userId) {
        List<CartGoods> cart = getCart(userId);
        for (CartGoods goods : cart) {
            if (goods.getId() == goodsId) {
                cartMapper.updateCart(userId, goodsId, amount);
                return;
            }
        }
        cartMapper.insertCartGoods(userId, goodsId, amount);
    }

    public List<CartGoods> getCart(int userId) {
        return cartMapper.selectCartGoods(userId);
    }

    public void deleteFromCart(int goodsId, int userId) {
        cartMapper.deleteCartGoods(userId, goodsId);
    }
}
