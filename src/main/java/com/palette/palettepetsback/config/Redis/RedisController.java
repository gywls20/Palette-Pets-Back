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

    @PostMapping("/save")
    public void save() {
        Test test = new Test("555", "changeTest22", LocalDateTime.now());
        redisRepository.save("2", test);
    }

    @GetMapping("/find")
    public Test find() {
        return redisRepository.findById("2");
    }
    @GetMapping("/findAll")
    public void findAll() {
        redisRepository.findAll();
    }

    @PostMapping("/delete")
    public void delete() {
        redisRepository.delete("1");
    }

    @PostMapping("/setWithExpiration")
    public void setWithExpiration() {
        Test test = new Test("1", "test",LocalDateTime.now());
        redisRepository.setWithExpiration("1", test, 10L, TimeUnit.SECONDS);
    }

    @GetMapping("/refresh/list")
    public List<RefreshToken> refreshList() {
        Iterable<RefreshToken> all = refreshTokenRepository.findAll();
        return (List<RefreshToken>) all;
    }
}
