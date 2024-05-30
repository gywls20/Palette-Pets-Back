package com.palette.palettepetsback.member.controller;


import com.palette.palettepetsback.config.jwt.filter.LoginFilter;
import com.palette.palettepetsback.member.dto.LoginRequest;
import com.palette.palettepetsback.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { //에러출력
            List<FieldError> list = bindingResult.getFieldErrors();
            for(FieldError error : list) {
                return new ResponseEntity<>(error.getDefaultMessage() , HttpStatus.BAD_REQUEST);
            }
        }
        //보안상 에러메시지는 간소화 했습니다.
        if (memberService.login(loginRequest.getEmail(), loginRequest.getPassword())==null) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return ResponseEntity.ok("로그인 성공");
    }

//    @GetMapping("/join")
//    public MemberResponseDto postMember(@c String memberId) {
//
//        return null;
//    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        return new ResponseEntity<>("테스트입니다", HttpStatus.OK);
    }

}
