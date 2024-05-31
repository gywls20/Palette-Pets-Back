package com.palette.palettepetsback.member.controller;


import com.palette.palettepetsback.config.jwt.filter.LoginFilter;
import com.palette.palettepetsback.member.dto.JoinRequest;
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

    @PostMapping("/join")
    public ResponseEntity<String> signup (@Valid @RequestBody JoinRequest joinRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //에러출력
            List<FieldError> list = bindingResult.getFieldErrors();
            for(FieldError error : list) {
                return new ResponseEntity<>(error.getDefaultMessage() , HttpStatus.BAD_REQUEST);
            }
        }
        if (memberService.checkEmailDuplicate(joinRequest.getEmail())) {
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
        }

        memberService.join(joinRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");


    }

    //닉네임 중복확인 버튼
    //중복시 true 반환
    @PostMapping("/checkNickname")
    public Boolean checkNickname(@RequestBody String nickname) {
        if (memberService.checkNicknameDuplicate(nickname)) {
            return true;
        }
        return false;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        return new ResponseEntity<>("테스트입니다", HttpStatus.OK);
    }

}
