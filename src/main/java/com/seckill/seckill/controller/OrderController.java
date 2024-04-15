// package com.seckill.seckill.controller;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.seckill.seckill.annotation.LoginRequired;
// import com.seckill.seckill.service.OrderService;
// import com.seckill.seckill.vo.RespBean;


// @Controller
// @RequestMapping("/order")
// public class OrderController {

//     @Autowired
//     private OrderService orderService;

//     @LoginRequired
//     @Transactional
//     @RequestMapping("/seckill/{goodsId}")
//     public String seckill(Model model, @PathVariable int goodsId) {

//         RespBean respBean = orderService.placeOrder(goodsId, 1);

//         if (respBean.getCode() != 200) {
//             model.addAttribute("error", respBean.getMessage());
//             return "order/seckillFail";
//         }
//         model.addAttribute("goods", ((Map) respBean.getObj()).get("goods"));
//         model.addAttribute("order", ((Map) respBean.getObj()).get("order"));
//         return "order/detail";
//     }

// }
