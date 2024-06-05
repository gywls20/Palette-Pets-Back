package com.palette.palettepetsback.Article.articleView.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.QArticle;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.Article.articleView.DTO.reponsse.ArticleResponseDTO;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.jpa.JPAExpressions.selectFrom;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final Integer PAGE_SIZE;

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

    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> searchList(PageableDTO pd){
        int offset = (pd.getPage()-1) * PAGE_SIZE;
        QArticle qArticle = QArticle.article;

        PathBuilder<?> entityPath = new PathBuilder<>(Article.class, "article"); // 나는 Article Entity를 조회할거야
        Order order = pd.getDir() ? Order.DESC : Order.ASC; // 오름차순, 내림차순 정하기
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>(); // 정렬 조건 모음
        orderSpecifiers.add(new OrderSpecifier(order, entityPath.get(pd.getSort()))); // 정렬 조건 넣기

        String[] searchList = pd.getWhere().split(","); // ',' 단위로 주어진 검색 조건 분리

        BooleanBuilder where = new BooleanBuilder(); // 검색 조건을 넣는 객체
//        for(String search: searchList){
//            where.or(qArticle.articleTags.like("%"+search+"%"));
//            // where articleTage like concat("%"+"고양이"+"%") or articleTage like concat("%"+"강아지"+"%")
//        }

        List<Article> articles = jpaQueryFactory
                .selectFrom(qArticle)
                .where(where)
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]))
                .offset(offset).limit(PAGE_SIZE)
                .fetch();
        System.out.println("offset : "+offset);
        System.out.println("Size : "+articles.size());

        List<ArticleResponseDTO> articleResponseDTOList = articles.stream()
                .map(responseDTO -> new ArticleResponseDTO(responseDTO))
                .collect(Collectors.toList());

        return articleResponseDTOList;
    }
    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> searchTest(String articleTags) {
        QArticle qArticle = QArticle.article;

        PathBuilder<?> entityPath = new PathBuilder<>(Article.class, "article"); // 나는 Article Entity를 조회할거야

        String[] searchList = articleTags.split(","); // ',' 단위로 주어진 검색 조건 분리

        BooleanBuilder where = new BooleanBuilder(); // 검색 조건을 넣는 객체
//        for(String search: searchList){
//            where.or(qArticle.articleTags.like("%"+search+"%"));
//            // where articleTage like concat("%"+"고양이"+"%") or articleTage like concat("%"+"강아지"+"%")
//        }
        List<Article> articleList = jpaQueryFactory
                .selectFrom(qArticle)
                .where(where)
                .fetch();
        List<ArticleResponseDTO> articleResponseDTOList = articleList.stream()
                .map(responseDTO -> new ArticleResponseDTO(responseDTO))
                .collect(Collectors.toList());
        return articleResponseDTOList;
    }
    @Transactional(readOnly = true)
    public Integer count(String where) {
        System.out.println(articleRepository.countByArticleTagsContaining(where).orElse(0));
        return articleRepository.countByArticleTagsContaining(where).orElse(0);
    }
}

