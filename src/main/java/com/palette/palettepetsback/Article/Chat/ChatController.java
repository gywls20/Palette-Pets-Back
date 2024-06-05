package com.palette.palettepetsback.Article.Chat;

import com.palette.palettepetsback.config.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatController {

    @GetMapping("/api/connect")
    private ResponseEntity<String> connect(HttpServletResponse response) throws IOException {
        System.out.println("22222222222222222222222222222222222222");
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("11111111111111111111111111111111111111");
        Long memberId = principal.getMember().getMemberId();

        String redirectUrl = "http://175.45.200.47:3000/chat/" + memberId;

        return ResponseEntity.ok().body(redirectUrl);
    }
}
