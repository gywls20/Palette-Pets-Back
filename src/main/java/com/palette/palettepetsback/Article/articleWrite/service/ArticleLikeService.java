package com.palette.palettepetsback.Article.articleWrite.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.ArticleLike;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleLikeRequestDto;
import com.palette.palettepetsback.Article.articleWrite.dto.response.ArticleLikeResponseDto;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleLikeRepository;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public void likeArticle(Long articleId,Long memberId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("게시글 찾을수없음"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("Member not found"));

        Optional<ArticleLike>existingLike= articleLikeRepository.findByArticleAndMember(article,member);
        if(existingLike.isPresent()){
            return;
        }

        ArticleLike articleLike = ArticleLike.builder()
                .article(article)
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();
        articleLikeRepository.save(articleLike);
    }

    public void unlikeArticle(Long articleId,Long memberId){
        Article article =articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("Article not found"));
        Member member =memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("Member not found"));

        Optional<ArticleLike>existingLike = articleLikeRepository.findByArticleAndMember(article,member);
        existingLike.ifPresent(articleLikeRepository::delete);
    }

    public List<ArticleLike>getArticleLikes(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("Article not found"));
        return articleLikeRepository.findByArticle(article);
    }
    public long getArticleLikeCount(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("Article not found"));
        return articleLikeRepository.countByArticle(article);
    }
}