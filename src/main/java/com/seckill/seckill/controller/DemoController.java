package com.seckill.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, World!"); // 在模板中可以使用 th:text="${message}" 获取该属性值
        return "hello"; // 返回的字符串 "hello" 对应的是Thymeleaf模板的文件名，比如 "hello.html"
    }
}
