package com.palette.palettepetsback.Article.articleWrite.repository;

import com.palette.palettepetsback.Article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleWriteRepository extends JpaRepository<Article,Long> {
//    Page<Article> findAll(Pageable pageable);
}
