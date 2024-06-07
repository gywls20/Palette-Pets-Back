package com.palette.palettepetsback.notification.controller;

import com.palette.palettepetsback.config.jwt.AuthInfoDto;
import com.palette.palettepetsback.config.jwt.jwtAnnotation.JwtAuth;
import com.palette.palettepetsback.notification.service.MemberIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberIssueController {
    // 알람 기능 컨트롤러

    private final MemberIssueService memberIssueService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(
            @RequestHeader(value = "Last_Event_ID", required = false, defaultValue = "") final String lastEventId,
            @JwtAuth AuthInfoDto authInfoDto
            ) {

        return ResponseEntity.ok(memberIssueService.connect(authInfoDto.getMemberId(), lastEventId));
    }

    @GetMapping("/sse/test1")
    public ResponseEntity<?> test1(@JwtAuth AuthInfoDto authInfoDto) {

        String coreFeatures = "emitter test";
        // 임의로 받는 사람에 테스트 계정 정보 넣어서 테스트
        memberIssueService.sendNotification(authInfoDto.getMemberId(), "알람 테스트입니당", 3);

        return ResponseEntity.ok(coreFeatures);
    }

}
