package com.palette.palettepetsback.Article.redis.controller;

import com.palette.palettepetsback.Article.redis.ArticleWriteRedis;
import com.palette.palettepetsback.Article.redis.LikeArticleRedis;
import com.palette.palettepetsback.Article.redis.ReportArticleRedis;
import com.palette.palettepetsback.Article.redis.service.ArticleRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleRedisController {

    private final ArticleRedisService articleRedisService;

    /**
     * 글 도배 방지 정보를 저장
     * @param articleWriteRedis 저장할 정보
     * @return 저장된 정보
     */
    @PostMapping("/ArticleRedis")
    public ArticleWriteRedis saveArticleWrite(@RequestBody ArticleWriteRedis articleWriteRedis){
        return articleRedisService.saveArticleWrite(articleWriteRedis);
    }

    /**
     * 글 좋아요 정보를 저장
     * @param likeArticleRedis 저장할 정보
     * @return 저장된 정보
     */
    @PostMapping("/like")
    public LikeArticleRedis saveLikeArticle(@RequestBody LikeArticleRedis likeArticleRedis){
        return articleRedisService.saveLikeArticle(likeArticleRedis);
    }

    /**
     * 글 신고 정보를 저장
     * @param reportArticleRedis 저장할 정보
     * @return 저장된 정보
     */
    @PostMapping("/report")
    public ReportArticleRedis saveReportArticle(@RequestBody ReportArticleRedis reportArticleRedis){
        return articleRedisService.saveReportArticle(reportArticleRedis);
    }
}
