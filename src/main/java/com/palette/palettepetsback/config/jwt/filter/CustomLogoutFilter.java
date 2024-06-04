package com.palette.palettepetsback.config.jwt.filter;

import com.palette.palettepetsback.config.jwt.JWTUtil;
import com.palette.palettepetsback.config.jwt.redis.RefreshTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 굳이 doFilter 두번 감싸는 이유 -> HttpServletRequest 얘 써야하는데 GenericFilterBean 구현하면 ServletRequest를 파라미터로 써야해서
     * 다운 캐스팅이 필요함
     * 또한 private doFilter는 굳이 외부에 보여줄 필요도 없으므로 이렇게 씀.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    // 실제 로그아웃 기능
    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 요청 경로 검증 - /logout 의 POST 요청
        if (!request.getRequestURI().matches("^\\/logout$")) {
            chain.doFilter(request, response);
            return;
        }
        if (!request.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }
        
        log.info("로그아웃 수행 시작");

        // refresh token 가져오기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        log.info("refresh 토큰 가져오기 = {}", refresh);

        // null check
        if (refresh == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("refresh token is null");
            return;
        }
        // 만료 체크 -> 이미 로그아웃 필요 에러 반환 (REFRESH_TOKEN_EXPIRED_ERROR)
        if (jwtUtil.isExpired(refresh)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().print("REFRESH_TOKEN_EXPIRED_ERROR");
            return;
        }
        // 받은 토큰이 refresh 인지 확인
        if (!jwtUtil.getCategory(refresh).equals("refresh")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("refresh token invalid");
            return;
        }

        /**
         * 로그아웃 로직 수행
         */
        // todo RTR 저장소 토큰 제거
        refreshTokenRepository.deleteByRefreshToken(refresh);

        // refresh 쿠키 제거
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        // 응답
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("logout success");
    }
}
