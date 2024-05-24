package com.palette.palettepetsback.articleView.service;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
