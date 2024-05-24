package com.palette.palettepetsback.articleView.service;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.entity.QArticle;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceExam {
    private final ArticleRepository articleRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final Integer PAGE_SIZE;


    @Transactional
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> queryDSLTestService(List<OrderSpecifier> orderSpecifiers, int page){
        int offset = (page-1) * PAGE_SIZE;
        int limit = offset + PAGE_SIZE;
        QArticle qArticle = QArticle.article;
        List<Article> articles = jpaQueryFactory
                .selectFrom(qArticle)
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]))
                .offset(offset).limit(limit)
                .fetch();
        return articles;
    }
}
