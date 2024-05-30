package com.palette.palettepetsback.config.jwt.redis.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

/**
 * 레디스 데이터 자료구조
 */

@Data
@RedisHash(value = "RefreshToken", timeToLive = 60)
public class RefreshToken {

    @Id
    private String refreshToken;
    @Indexed
    private String email; // 중복 가능 -> not unique
    @TimeToLive
    private Long expiration;

    @Builder
    public RefreshToken(String refreshToken, String email, Long expiration) {
        this.refreshToken = refreshToken;
        this.email = email;
        this.expiration = expiration;
    }
}
