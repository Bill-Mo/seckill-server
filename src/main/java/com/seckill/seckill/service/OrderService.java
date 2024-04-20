package com.seckill.seckill.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seckill.seckill.dao.OrderMapper;
import com.seckill.seckill.entity.CartGoods;
import com.seckill.seckill.entity.Goods;
import com.seckill.seckill.entity.Order;
import com.seckill.seckill.entity.OrderGoods;
import com.seckill.seckill.util.HostHolder;
import com.seckill.seckill.util.RedisUtil;
import com.seckill.seckill.vo.RespBean;
import com.seckill.seckill.vo.RespBeanEnum;

@Service
public class OrderService {

    // logger
    @Autowired
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    CartService cartService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional
    public RespBean checkout(int userId, String address, List<CartGoods> cart) {
        Order order = new Order();

        // Compute total price
        int totalPrice = 0;
        for (CartGoods cartGoods : cart) {
            if (cartGoods.getStatus() == 1) {
                totalPrice += cartGoods.getPrice() * cartGoods.getAmount();
            }
        }

        // Set and insert order
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setAddress(address);
        order.setCreateTime(new Date());
        orderMapper.insertOrder(order);

        for (CartGoods cartGoods : cart) {
            if (cartGoods.getStatus() == 1) {

                // Add goods into order, update goods stock, and remove goods from cart, 
                int result = orderMapper.insertOrderGoods(order.getId(), cartGoods.getId(), cartGoods.getAmount(), cartGoods.getPrice());
                RespBean reduceStock = goodsService.updateGoodsStock(userId, -cartGoods.getAmount());
                if (result != 1 || reduceStock.getCode() != 200) {
                    return RespBean.error(RespBeanEnum.ORDER_FAIL);
                }
                cartService.deleteFromCart(cartGoods.getId(), userId);
            }
        }

        return RespBean.success(order.getId());
    }

    public Order getOrder(int userId, int orderId) {
        return orderMapper.selectOrderById(userId, orderId);
    }

    public List<OrderGoods> getOrderGoods(int orderId) {
        return orderMapper.selectOrderGoods(orderId);
    }
}
