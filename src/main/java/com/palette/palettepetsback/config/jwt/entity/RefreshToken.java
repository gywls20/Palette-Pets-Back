package com.palette.palettepetsback.config.jwt.entity;

/**
 * 나중에 레디스로 관리할거
 */
public class RefreshToken {

    // refresh_token_id
    private Long id;
    private String email; // 중복 가능 -> not unique
    private String refreshToken;
    private String expiration;

    public RefreshToken(String email, String refreshToken, String expiration) {
        this.email = email;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
