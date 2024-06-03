package com.palette.palettepetsback.config.Redis;

import com.palette.palettepetsback.config.jwt.redis.RefreshTokenRepository;
import com.palette.palettepetsback.config.jwt.redis.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {
    private final redisRepository redisRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @GetMapping("/refresh/list")
    public List<RefreshToken> refreshList() {
        Iterable<RefreshToken> all = refreshTokenRepository.findAll();
        return (List<RefreshToken>) all;
    }
}
