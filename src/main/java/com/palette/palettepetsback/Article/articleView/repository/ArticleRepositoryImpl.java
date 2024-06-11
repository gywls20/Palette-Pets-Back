package com.palette.palettepetsback.Article.articleView.repository;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.QArticle;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> findDsl(PageableDTO pd) {
        QArticle article = QArticle.article;

        int page = pd.getPage();
        int size = 10;
        String sort = pd.getSort();
//        boolean dir = pd.isDir();

        Pageable pageable = PageRequest.of(page - 1, size);

        JPAQuery<Article> content = queryFactory
                .select(Projections.constructor(Article.class,
                        article.articleId,
                        article.createdAt,
                        article.content,
                        article.articleTags,
                        article.title,
                        article.countLoves))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


//        switch (sort) {
//            case "articleId" -> {
//                if (dir) {
//                    content.orderBy(article.articleId.asc());
//                } else content.orderBy(article.articleId.desc());
//            }
//            case "createdAt" -> {
//                if (dir) {
//                    content.orderBy(article.createdAt.asc());
//                } else content.orderBy(article.createdAt.desc());
//            }
//            case "countLoves" -> {
//                if (dir) {
//                    content.orderBy(article.countLoves.asc());
//                } else content.orderBy(article.countLoves.desc());
//            }
//            default -> content.orderBy(article.articleId.asc());
//        }

        List<Article> articles = content.fetch();

        return new PageImpl<>(articles);
    }
}

