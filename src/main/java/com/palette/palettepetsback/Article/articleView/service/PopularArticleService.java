package com.palette.palettepetsback.Article.articleView.service;

import com.palette.palettepetsback.Article.articleView.DTO.reponsse.PopularArticleDTO;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularArticleService {
    private final ArticleRepository articleRepository;

    public List<PopularArticleDTO> likeArticle(){
        return articleRepository.findPopularArticleByDate(LocalDate.now().minusDays(3));
    }
}
