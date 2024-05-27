package com.palette.palettepetsback.config.SingleTon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Singleton {

    @Bean
    public Integer PAGE_SIZE(){
        return 10;
    }


    @Bean
    public String BUCKET_NAME(){
        return "palettepets";
    }
}
