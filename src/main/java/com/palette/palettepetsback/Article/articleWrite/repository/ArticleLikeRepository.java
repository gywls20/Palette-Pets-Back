package com.palette.palettepetsback.Article.articleWrite.repository;

import com.palette.palettepetsback.Article.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
//    Optional<ArticleLike> findByArticleIdAndCreatedWho(Long articleId, Long createdWho);
//    List<ArticleLike> findByArticleId(Long articleId);
}