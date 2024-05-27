package com.palette.palettepetsback.articleView.controller;

import com.palette.palettepetsback.articleView.DTO.Test.ArticleSaveDTO;
import com.palette.palettepetsback.articleView.DTO.Test.QueryDSLDTO;
import com.palette.palettepetsback.articleView.DTO.Test.SearchDSLDTO;
import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.service.ArticleServiceExam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private final ArticleServiceExam articleService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, World!";
    }

    @PostMapping("/save/article")
    public ResponseEntity<?> saveArticle(@RequestBody ArticleSaveDTO request){
        Article article = articleService.saveArticle(Article.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .createdWho(1L)
                .articleTags(request.getTags())
                .build());
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("/test/querydsl")
    public ResponseEntity<?> showQueryDSL(@ModelAttribute QueryDSLDTO request){

        PathBuilder<?> entityPath = new PathBuilder<>(Article.class, "article");
        Order order = request.isAsc() ? Order.ASC : Order.DESC;
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        orderSpecifiers.add(new OrderSpecifier(order, entityPath.get(request.getSort())));

        List<Article> articles = articleService.queryDSLTestService(orderSpecifiers, request.getPage());
        System.out.println(articles.size());

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/test/querydsl/search")
    public ResponseEntity<?> searchDSL(@ModelAttribute SearchDSLDTO request){
        String[] searchList = request.getSearch().split(",");
        PathBuilder<?> entityPath = new PathBuilder<>(Article.class, "article");
        Order order = request.isAsc() ? Order.ASC : Order.DESC;
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        orderSpecifiers.add(new OrderSpecifier(order, entityPath.get(request.getSort())));
        List<Article> articles = articleService.queryDSLTestSearch(orderSpecifiers, request.getPage(), searchList);

        return ResponseEntity.ok().body(articles);
    }
}
