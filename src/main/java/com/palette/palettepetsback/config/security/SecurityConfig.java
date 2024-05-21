package com.palette.palettepetsback.config.security;

import com.palette.palettepetsback.config.jwt.JWTFilter;
import com.palette.palettepetsback.config.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 보안 및 로그인 방식 관리
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors((cors) -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                    configuration.setAllowedMethods(Collections.singletonList("*"));
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setAllowCredentials(true);
                    configuration.setMaxAge(3600L);
                    // 클라이언트에 인증 헤더를 보낼 때 허용 필수
                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                    return configuration;
                    }))
//                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
        // 경로 별 인가
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/logout", "/", "/members/**").permitAll()
                        .anyRequest().permitAll()
//                        .anyRequest().authenticated()
                );
        // jwt 관련 필터들 적용
//        http
//                .addFilterBefore(new JWTFilter(), LoginFilter.class);

        return http.build();
    }
}
