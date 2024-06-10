package com.palette.palettepetsback.config.Mail;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final RegisterMail registerMail;
    @PostMapping("/login/mailConfirm")
    public ResponseEntity<EmailResponseDTO> mailConfirm(@RequestBody @Valid EmailRequestDTO request) throws Exception {
        System.out.println("이메일 : " + request.email);
        String data = registerMail.sendEmail(request.email);
        System.out.println("인증코드 : " + data);
        if(data != null) {
            return ResponseEntity.ok().body(new EmailResponseDTO(data));
        }
        return ResponseEntity.ok().body(new EmailResponseDTO(null));
    }

}