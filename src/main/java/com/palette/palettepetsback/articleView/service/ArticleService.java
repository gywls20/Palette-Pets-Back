package com.palette.palettepetsback.articleView.service;

import com.palette.palettepetsback.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
