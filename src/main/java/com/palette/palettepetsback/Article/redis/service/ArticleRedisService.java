package com.palette.palettepetsback.Article.redis.service;

import com.palette.palettepetsback.Article.redis.ArticleWriteRedis;
import com.palette.palettepetsback.Article.redis.LikeArticleRedis;
import com.palette.palettepetsback.Article.redis.ReportArticleRedis;
import com.palette.palettepetsback.Article.redis.repository.ArticleWriteRedisRepository;
import com.palette.palettepetsback.Article.redis.repository.ArticleLikeRedisRepository;
import com.palette.palettepetsback.Article.redis.repository.ArticleReportRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ArticleRedisService {

    private final ArticleWriteRedisRepository articleWriteRedisRepository;
    private final ArticleLikeRedisRepository articleLikeRedisRepository;
    private final ArticleReportRedisRepository articleReportRedisRepository;

    /**
     * 글 도배 방지 정보를 저장.
     * @param articleWriteRedis 저장할 정보
     * @return 저장된 정보
     */
    public ArticleWriteRedis saveArticleWrite(ArticleWriteRedis articleWriteRedis){
        return articleWriteRedisRepository.save(articleWriteRedis);
    }

    /**
     * 글 신고 정보를 저장
     * @param likeArticleRedis 저장할 정보
     * @return 저장된 정보
     */
    public LikeArticleRedis saveLikeArticle(LikeArticleRedis likeArticleRedis){
        return articleLikeRedisRepository.save(likeArticleRedis);
    }

    /**
     *  글 신고 정보를 저장
     * @param reportArticleRedis 저장할 정보
     * @return 저장된 정보
     */
    public ReportArticleRedis saveReportArticle(ReportArticleRedis reportArticleRedis){
        return articleReportRedisRepository.save(reportArticleRedis);
    }
}
