package com.palette.palettepetsback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**")
                .allowedOriginPatterns("*") //모든 도메인 허용
                .allowedMethods("*") // 모든 HTTP 메서드 허용
                .allowCredentials(true)
                .allowedHeaders("*");// 모든 HTTP 헤더 허용
    }
}
