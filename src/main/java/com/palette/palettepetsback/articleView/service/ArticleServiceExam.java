package com.palette.palettepetsback.articleView.service;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceExam {
    private final ArticleRepository articleRepository;

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
