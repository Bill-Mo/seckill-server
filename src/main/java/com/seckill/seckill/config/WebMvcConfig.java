package com.seckill.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.seckill.seckill.controller.interceptor.LoginInterceptor;
import com.seckill.seckill.controller.interceptor.TokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    // @Autowired
    // private MessageInterceptor messageInterceptor;

    // @Autowired
    // private DataInterceptor dataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
        .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
        
        registry.addInterceptor(loginInterceptor)
        .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
        // registry.addInterceptor(messageInterceptor)
        // .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        // registry.addInterceptor(dataInterceptor)
        // .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
	}
}
