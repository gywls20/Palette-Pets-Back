package com.palette.palettepetsback.articleView.repository;

import com.palette.palettepetsback.articleView.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
