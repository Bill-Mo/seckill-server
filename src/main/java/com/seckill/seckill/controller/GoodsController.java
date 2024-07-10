package com.seckill.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        model.addAttribute("goods", goodsService.findGoodsById(id));
        System.out.println(goodsService.findGoodsById(id));
        return "goods/detail";
    }

}
