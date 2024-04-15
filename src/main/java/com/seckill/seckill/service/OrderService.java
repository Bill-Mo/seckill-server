// package com.seckill.seckill.service;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// import org.slf4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.seckill.seckill.dao.OrderMapper;
// import com.seckill.seckill.entity.Goods;
// import com.seckill.seckill.entity.Order;
// import com.seckill.seckill.util.HostHolder;
// import com.seckill.seckill.util.RedisUtil;
// import com.seckill.seckill.vo.RespBean;
// import com.seckill.seckill.vo.RespBeanEnum;

// @Service
// public class OrderService {

//     // logger
//     @Autowired
//     private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);

//     @Autowired
//     private OrderMapper orderMapper;

//     @Autowired
//     private HostHolder hostHolder;

//     @Autowired
//     private GoodsService goodsService;

//     @Autowired
//     private RedisTemplate<String, Object> redisTemplate;

//     @Transactional
//     public RespBean placeOrder(int goodsId, int isSeckill) {
//         Order order = new Order();

//         if (isSeckill == 1) {
//             // Test if the stock is empty
//             Goods seckillGoodsVo = goodsService.findGoodsById(goodsId);
//             System.out.println(seckillGoodsVo.toString());
//             if (seckillGoodsVo.getSeckillStock() <= 0) {
//                 return RespBean.error(RespBeanEnum.EMPTY_STOCK);
//             }
            
//             // Test if the user has already bought the goods
//             if (redisTemplate.opsForSet().isMember(RedisUtil.getSeckillHistoryKey(goodsId), hostHolder.getUser().getUsername()) == true) {
//                 return RespBean.error(RespBeanEnum.REPEATE_ERROR);
//             }
            
//             order.setTotalPrice(seckillGoodsVo.getSeckillPrice());
//             order.setUserId(hostHolder.getUser().getId());
//             order.setAddress(hostHolder.getUser().getAddress());
//             order.setCreateTime(new Date());
//             order.setIsSeckill(isSeckill);

//             goodsService.updateSeckillGoodsStock(goodsId, -1);
//             orderMapper.insertOrder(order);

//             log.info("orderId: " + order.getId());
//             redisTemplate.opsForSet().add(RedisUtil.getSeckillHistoryKey(goodsId), hostHolder.getUser().getUsername());

//             Map<String, Object> map = new HashMap<>();
//             map.put("order", order);
//             map.put("goods", seckillGoodsVo);
//             return RespBean.success(map);
//         } else {
//             return RespBean.error(RespBeanEnum.ERROR);
//         }
//     }

//     // public Order selectOrder(int userId, int goodsId) {
//     //     return orderMapper.selectOrder(userId, goodsId);
//     // }
// }
