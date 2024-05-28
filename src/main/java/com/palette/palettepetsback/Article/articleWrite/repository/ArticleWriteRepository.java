package com.palette.palettepetsback.Article.articleWrite.repository;

import com.palette.palettepetsback.Article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleWriteRepository extends JpaRepository<Article,Long> {
}
