package com.palette.palettepetsback.feed.controller;

import com.palette.palettepetsback.config.jwt.AuthInfoDto;
import com.palette.palettepetsback.config.jwt.jwtAnnotation.JwtAuth;
import com.palette.palettepetsback.config.security.CustomUserDetails;
import com.palette.palettepetsback.feed.dto.FeedRequest;
import com.palette.palettepetsback.feed.entity.Feed;
import com.palette.palettepetsback.feed.service.FeedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/feed")
    public ResponseEntity<?> savefeed(@RequestPart(name="request") @Valid FeedRequest feedRequest,
                                      @RequestPart(required = false, name="files") MultipartFile[] files,
                                      @JwtAuth AuthInfoDto authInfoDto) {
        Long memberId=authInfoDto.getMemberId();

        int i = 0;
        // 글자와 멤버 아이디로 피드 저장
        Feed feed = feedService.saveFeed(feedRequest.getText(), memberId);

        // 파일 업로드 및 FeedImg 저장
        for (MultipartFile file : files) {
            String imgUrl = feedService.fileUpload(file, "feed/img");
            feedService.saveFeedImg(imgUrl, feed);
        }
        return ResponseEntity.ok("피드 작성 완료.");

    }
}
