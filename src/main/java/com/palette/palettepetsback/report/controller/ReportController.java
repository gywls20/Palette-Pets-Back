package com.palette.palettepetsback.report.controller;

import com.palette.palettepetsback.config.jwt.AuthInfoDto;
import com.palette.palettepetsback.config.jwt.jwtAnnotation.JwtAuth;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.report.dto.MemberReportAddRequest;
import com.palette.palettepetsback.report.entity.MemberReport;
import com.palette.palettepetsback.report.service.MemberReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final MemberReportService memberReportService;

    // 신고하는 사람의 Id
    @GetMapping("/getMemberId")
    public ResponseEntity<Long> getMemberId(@JwtAuth AuthInfoDto authInfoDto) {
        log.info("authInfoDto: {}", authInfoDto);
        log.info("...");
        return ResponseEntity.ok().body(authInfoDto.getMemberId());
    }

    @PostMapping("/addReport")
    public ResponseEntity<MemberReport> addReport(@RequestBody MemberReportAddRequest memberReportAddRequest) {
        log.info("MemberReportAddRequest: {}", memberReportAddRequest);

        memberReportService.addReport(memberReportAddRequest);
        return null;
    }

}
