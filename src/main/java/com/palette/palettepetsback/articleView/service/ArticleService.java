package com.palette.palettepetsback.articleView.service;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<Article> getList() {
        System.out.println(articleRepository.findAll());
        return articleRepository.findAll();
    }
}
