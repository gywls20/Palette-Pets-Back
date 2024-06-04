package com.palette.palettepetsback.Article.articleView.repository;

import com.palette.palettepetsback.Article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Query("SELECT count(a) FROM Article a WHERE a.articleTags LIKE %?1%")
    Optional<Integer> countByArticleTagsContaining(String tag);
}
