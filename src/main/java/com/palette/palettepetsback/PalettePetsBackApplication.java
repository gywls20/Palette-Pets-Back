package com.palette.palettepetsback;

import com.palette.palettepetsback.member.dto.Role;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class PalettePetsBackApplication {

    // 테스트용임 -> 곧 지워야 함
    private final MemberRepository memberRepository;

    public PalettePetsBackApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PalettePetsBackApplication.class, args);
    }

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
}
