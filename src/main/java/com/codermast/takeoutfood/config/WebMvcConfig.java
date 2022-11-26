package com.codermast.takeoutfood.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
     * @Description: 静态资源映射
     * @param registry
     * @Author: CoderMast https://www.codermast.com/
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("静态资源开始映射...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/"); // 后台
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");     // 前台
    }

}
