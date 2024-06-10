package com.palette.palettepetsback.feed.service;

import com.palette.palettepetsback.config.SingleTon.Singleton;
import com.palette.palettepetsback.config.Storage.NCPObjectStorageService;
import com.palette.palettepetsback.feed.repository.FeedImgRepository;
import com.palette.palettepetsback.feed.repository.FeedRepository;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import com.palette.palettepetsback.feed.entity.Feed;
import com.palette.palettepetsback.feed.entity.FeedImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedService {
    private final NCPObjectStorageService objectStorageService;
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final FeedImgRepository feedImgRepository;


    public String fileUpload(MultipartFile file, String dirPath) {
        return objectStorageService.uploadFile(Singleton.S3_BUCKET_NAME, dirPath, file);
    }

    public Feed saveFeed(String text, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            Feed feed = new Feed();
            feed.saveFeed(text, member.get());
            return feedRepository.save(feed);
        } else {
            throw new IllegalArgumentException("Member not found with id: " + memberId);
        }
    }

    public FeedImg saveFeedImg(String imgUrl, Feed feed) {
        FeedImg feedImg = new FeedImg();
        feedImg.saveImg(imgUrl,feed);
        return feedImgRepository.save(feedImg);
    }

    //피드삭제

    //피드리스트 리스폰
}
