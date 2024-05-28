package com.palette.palettepetsback.articleComment.repository;

import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
    List<ArticleComment> findByArticleId(Long articleId);
}
