package com.seckill.seckill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.seckill.entity.Page;
import com.seckill.seckill.service.GoodsService;
import com.seckill.seckill.vo.GoodsVo;

@Controller
public class HomeController {

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping("/index")
    public String list(Model model, Page page) {
        page.setRows(goodsService.findGoodsRows());
        page.setPath("/index");

        List<GoodsVo> goodsList = goodsService.findGoods(page.getOffset(), page.getLimit());
        System.out.println(goodsList);

        model.addAttribute("goodsList", goodsList);
        return "index";
    }
}
