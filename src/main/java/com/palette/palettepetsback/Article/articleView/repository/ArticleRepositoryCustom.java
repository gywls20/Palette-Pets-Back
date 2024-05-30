package com.palette.palettepetsback.Article.articleView.repository;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<Article> findDsl(PageableDTO pd);
}
