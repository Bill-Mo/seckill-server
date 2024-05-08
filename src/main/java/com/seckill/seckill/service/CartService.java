package com.seckill.seckill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.seckill.dao.CartMapper;
import com.seckill.seckill.entity.CartGoods;
import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsService goodsService; 
    
    public RespBean addToCart(int userId, int goodsId, int amount) {
        List<CartGoods> cart = getCart(userId);
        for (CartGoods goods : cart) {
            if (goods.getId() == goodsId) {
                updateCartGoodsAmount(userId, goodsId, amount);
                return RespBean.success();
            }
        }
        if (cartMapper.insertCartGoods(userId, goodsId, amount) == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.INSERTION_ERROR);
        }
    }

    public List<CartGoods> getCart(int userId) {
        return cartMapper.selectCartGoods(userId);
    }

    public RespBean deleteFromCart(int goodsId, int userId) {
        if (cartMapper.deleteCartGoods(userId, goodsId) != 1) {
            return RespBean.error(RespBeanEnum.DELETE_CART_ERROR);
        }
        return RespBean.success();
    }

    public RespBean updateCartGoodsAmount(int userId, int goodsId, int amount) {
        Goods goods = goodsService.findGoodsById(goodsId);
        CartGoods cartGoods = cartMapper.selectCartGoodsByGoodsId(userId, goodsId);
        int newAmount = amount + cartGoods.getAmount();

        newAmount = Math.min(newAmount, Math.min(goods.getStock(), goods.getPurchaseLimit()));
        int res = cartMapper.updateCartGoodsAmount(userId, goodsId, newAmount);
        if (res == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.UPDATE_CART_ERROR);
        }
    }

    public RespBean changeCartGoodsStatus(int userId, int goodsId) {
        int result = cartMapper.updateCartGoodsStatus(userId, goodsId);
        if (result == 1) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.UPDATE_CART_ERROR);
        }
    }
}
