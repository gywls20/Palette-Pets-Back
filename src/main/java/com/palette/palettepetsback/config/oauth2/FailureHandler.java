package com.palette.palettepetsback.config.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "로그인 실패.";

        if (exception.getCause() != null) {
            errorMessage = exception.getCause().getMessage();
        }

        // URL 인코딩 추가
        String encodedErrorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString());
        response.sendRedirect("http://localhost:3000/login?error=true&exception=" + encodedErrorMessage);
    }
}

