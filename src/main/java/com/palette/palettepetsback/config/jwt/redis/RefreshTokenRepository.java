package com.palette.palettepetsback.config.jwt.redis;

import com.palette.palettepetsback.config.jwt.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    List<RefreshToken> findAllByEmail(String email);
    List<RefreshToken> findAllByRefreshToken(String refreshToken);
    void deleteByRefreshToken(String refreshToken);
    boolean existsByRefreshToken(String refreshToken);
}
