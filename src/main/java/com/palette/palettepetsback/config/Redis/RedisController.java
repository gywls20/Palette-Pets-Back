package com.palette.palettepetsback.config.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {
    private final redisRepository redisRepository;

    @PostMapping("/save")
    public void save() {
        Test test = new Test("555", "changeTest22");
        redisRepository.save("2", test);
    }

    @GetMapping("/find")
    public Test find() {
        return redisRepository.findById("2");
    }

    @PostMapping("/delete")
    public void delete() {
        redisRepository.delete("1");
    }

    @PostMapping("/setWithExpiration")
    public void setWithExpiration() {
        Test test = new Test("1", "test");
        redisRepository.setWithExpiration("1", test, 10L, TimeUnit.SECONDS);
    }
}
