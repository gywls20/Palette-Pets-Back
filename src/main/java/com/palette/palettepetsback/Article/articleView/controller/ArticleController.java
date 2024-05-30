package com.palette.palettepetsback.Article.articleView.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.QArticle;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.Article.articleView.DTO.Test.QueryDSLDTO;
import com.palette.palettepetsback.Article.articleView.service.ArticleService;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    //@Autowired
    private final ArticleService articleService;
    private final JPAQueryFactory queryFactory;

    private final Integer PAGE_SIZE;

    @GetMapping("/")
    public ResponseEntity<?> getList(@ModelAttribute PageableDTO request) {
        int startPage = (request.getPage()-1) * PAGE_SIZE;
        Sort sort = Sort.by(Sort.Direction.ASC, request.getSort());
        Pageable pageable = PageRequest.of(startPage,startPage + 10, sort);

        Page<Article> articles = articleService.getList(pageable);

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Article>> getTest() {
        List<Article> articles = articleService.getTest();

        return ResponseEntity.ok().body(articles);
    }

    //리스트 출력하기
    @GetMapping("/list")
    public ResponseEntity<Page<Article>> queryDSL(@ModelAttribute PageableDTO pd) {
//        Pageable pageable = PageRequest.of(pd.getPage(),
//                                        10,
//                                        Sort.by(Sort.Direction.fromString(pd.getDir()), pd.getSort()));

        Page<Article> articles = articleService.getArticles(pd);

        return ResponseEntity.ok(articles);

    }

}
