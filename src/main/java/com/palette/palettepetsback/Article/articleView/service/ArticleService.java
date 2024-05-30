package com.palette.palettepetsback.Article.articleView.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> getTest() {
        return articleRepository.findAll();
    }

    public Page<Article> getArticles(PageableDTO pd) {
        return articleRepository.findDsl(pd);
    }
}

