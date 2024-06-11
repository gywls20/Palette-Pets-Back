package com.palette.palettepetsback.articleComment.repository;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
    // select * from article_comment where article_id = Article.article_id
    List<ArticleComment> findByArticleOrderByParentIdAscRefAsc(Article articleId);

    @Query("SELECT MAX(e.articleCommentId) FROM ArticleComment e")
    Long findMaxId();
}

