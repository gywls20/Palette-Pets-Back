package com.palette.palettepetsback.config.jwt.reissue;

import com.palette.palettepetsback.config.jwt.JWTUtil;
import com.palette.palettepetsback.config.jwt.redis.RefreshTokenRepository;
import com.palette.palettepetsback.config.jwt.redis.entity.RefreshToken;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 재발급 서비스
 * - 어세스 토큰이 만료 / 리프레시 토큰이 유효할 때 -> 재발급
 * - RTR 방식 -> 발급 후, 리프레시 토큰도 재발급 (일회용) + 저장소에 이전 리프레시 토큰 삭제 / 현재 리프레시 토큰 저장
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueService {
    
    private final JWTUtil jwtUtil;
    // todo : RTR 저장소 
    private final RefreshTokenRepository refreshTokenRepository;
    
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        
        // refresh 토큰 저장 쿠키 꺼내기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        // 리프레시 토큰 유효성 검사
        if (refresh == null) {
            return new ResponseEntity<>("refresh token is null", HttpStatus.BAD_REQUEST);
        }
        // 만료 체크
        if (jwtUtil.isExpired(refresh)) {
            // 만료된 리프레시 토큰은 서버에서 제거
            Cookie cookie = new Cookie("refresh", null);
            cookie.setMaxAge(0);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            // REFRESH_TOKEN_EXPIRED_ERROR 에러를 프론트에 반환
            return new ResponseEntity<>("REFRESH_TOKEN_EXPIRED_ERROR", HttpStatus.BAD_REQUEST);
        }
        // refresh 토큰인지 category 검증
        if (!jwtUtil.getCategory(refresh).equals("refresh")) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        // 기존 리프레시 토큰이 토큰 저장소에 존재하는 지 검증 todo 추후 추가
        Boolean isExist = refreshTokenRepository.existsByRefreshToken(refresh);
        if (!isExist) {
            return new ResponseEntity<>("refresh token does not exist", HttpStatus.BAD_REQUEST);
        }
        
        // 리프레시 토큰에서 새롭게 발행할 토큰의 정보들 추출
        Claims claims = jwtUtil.getClaims(refresh);
        // map에 담기 -> JWT Claims 는 변경 불가하므로 category 추가가 불가능
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", claims.get("memberId", Long.class));
        map.put("email", claims.get("email", String.class));
        map.put("role", claims.get("role", String.class));

        // 어세스 & 리프레시 토큰 둘 다 재발급 -> RTR
        String newAccess = jwtUtil.generateToken("access", map, 10 * 60 * 1000L);
        String newRefresh = jwtUtil.generateToken("refresh", map, 24 * 60 * 60 * 1000L);
        
        // todo 저장소 : 기존 리프레시 토큰 삭제 & 새로운 리프레시 토큰 저장
        refreshTokenRepository.deleteByRefreshToken(refresh);
        addRefreshTokenRepository(claims.get("email", String.class), newRefresh, 24 * 60 * 60 * 1000L);

        // 어세스 토큰 & 리프레시 토큰 응답에 넣어 반환
        response.setHeader("Authorization", "Bearer " + newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        log.info("토큰 재발급 수행 ...");
        log.info("newAccess : '{}'", newAccess);
        log.info("newRefresh : '{}'", newRefresh);

        return new ResponseEntity<>("리프레시 토큰 재발급 성공", HttpStatus.OK);
    }

    // 쿠키 생성 메서드
    private Cookie createCookie(String key, String token) {

        Cookie cookie = new Cookie(key, token);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setSecure(true);

        return cookie;
    }

    private void addRefreshTokenRepository(String email, String newRefresh, Long expiredMs) {
        // 1. 현재날짜 + 유통 기한 더 한 날짜 구하기
        // 2. 리프레시 토큰 엔티티 생성 및 저장
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(newRefresh)
                .email(email)
                .expiration(date.getTime())
                .build();

        refreshTokenRepository.save(refreshToken);
    }

    // todo : 근데 redis 이거 어디서 확인함? TEST 방법 시급함
}
