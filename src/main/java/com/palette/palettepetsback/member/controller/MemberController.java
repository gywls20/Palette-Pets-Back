package com.palette.palettepetsback.member.controller;

import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
import com.palette.palettepetsback.member.dto.response.MemberResponseDto;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public Member getMembers() {

        return memberService.test("test");
    }

    @GetMapping("/{id}")
    public MemberResponseDto getMemberByMemberId(@PathVariable String memberId) {

        return null;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        return new ResponseEntity<>("테스트입니다", HttpStatus.OK);
    }

}
