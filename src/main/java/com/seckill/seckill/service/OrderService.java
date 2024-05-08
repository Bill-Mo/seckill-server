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
        double totalPrice = 0;
        for (CartGoods cartGoods : cart) {
            if (cartGoods.getStatus() == 1) {
                totalPrice += cartGoods.getPrice() * cartGoods.getAmount();
            }
        }

        if (address == null || address.length() == 0) {
            return RespBean.error(RespBeanEnum.INVALID_USER);
        }

        if (totalPrice == 0 || cart.size() == 0){
            return RespBean.error(RespBeanEnum.INVALID_CART);
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
                RespBean reduceStock = goodsService.updateGoodsStock(cartGoods.getId(), -cartGoods.getAmount());
                if (result != 1 || reduceStock.getCode() != 200) {
                    return RespBean.error(RespBeanEnum.ORDER_FAIL);
                }
                cartService.deleteFromCart(cartGoods.getId(), userId);
            }
        }

        return RespBean.success(order.getId());
    }

    @Transactional
    public RespBean checkout(int userId, String address, int goodsId, int amount) {
        Order order = new Order();
        Goods goods = goodsService.findGoodsById(goodsId);
        if (goods == null) {
            return RespBean.error(RespBeanEnum.GOODS_NOT_FOUND);
        }

        // Compute total price
        double totalPrice = goods.getPrice() * amount;

        if (address == null || address.length() == 0) {
            return RespBean.error(RespBeanEnum.INVALID_USER);
        }
        
        // Set and insert order
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setAddress(address);
        order.setCreateTime(new Date());
        orderMapper.insertOrder(order);

        // Add goods into order, update goods stock, and remove goods from cart, 
        int result = orderMapper.insertOrderGoods(order.getId(), goods.getId(), amount, goods.getPrice());
        RespBean reduceStock = goodsService.updateGoodsStock(goods.getId(), -amount);
        if (result != 1 || reduceStock.getCode() != 200) {
            return RespBean.error(RespBeanEnum.ORDER_FAIL);
        }
        return RespBean.success(order.getId());
    }

    public Order getOrder(int userId, int orderId) {
        return orderMapper.selectOrderById(userId, orderId);
    }

    public List<OrderGoods> getOrderGoods(int orderId) {
        return orderMapper.selectOrderGoods(orderId);
    }

    public List<Order> getUserOrders(int userId, int offset, int limit, int mode) {
        return orderMapper.selectOrders(userId, offset, limit, mode);
    }

    public int countUserOrders(int userId, int mode) {
        return orderMapper.selectOrderRows(userId, mode);
    }

    public RespBean placeOrder(String paymentMethod, int userId, int orderId) {
        int result = orderMapper.placeOrder(paymentMethod, userId, orderId);
        if (result == 1) {
            Order order = getOrder(userId, orderId);
            return RespBean.success(order.getTotalPrice());
        }
        return RespBean.error(RespBeanEnum.ORDER_FAIL);
    }

    public RespBean updateOrderAddress(int orderId, String address) {
        int result = orderMapper.updateOrderAddress(orderId, address);
        if (result == 1) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.UPDATE_ORDER_ERROR);
    }
}
