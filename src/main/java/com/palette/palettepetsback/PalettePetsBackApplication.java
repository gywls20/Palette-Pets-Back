package com.palette.palettepetsback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
>>>>>>> 946a83eb65948bcf6e16908d2445cd6111cce26a

@SpringBootApplication
public class PalettePetsBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalettePetsBackApplication.class, args);
    }

<<<<<<< HEAD
=======
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

//    @PostConstruct
//    public void init() {
//        memberRepository.save(
//                Member.builder()
//                        .memberId("test")
//                        .name("김승원")
//                        .password("1234")
//                        .role(Role.USER)
//                        .build()
//        );
//    }
>>>>>>> 946a83eb65948bcf6e16908d2445cd6111cce26a
}
