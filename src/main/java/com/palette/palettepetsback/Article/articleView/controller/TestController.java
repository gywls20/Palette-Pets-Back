package com.palette.palettepetsback.Article.articleView.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.service.ArticleServiceExam;
import com.palette.palettepetsback.Article.articleView.DTO.Test.ArticleSaveDTO;
import com.palette.palettepetsback.Article.articleView.DTO.Test.QueryDSLDTO;
import com.palette.palettepetsback.Article.articleView.DTO.Test.SearchDSLDTO;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
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
//                .articleTags(request.getTags())
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

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        String input = "아버지가방구에들어가신다";
        KomoranResult analyzeResultList = komoran.analyze(input);
        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            if ("NNG".equals(token.getPos())) { // NNG 품사만 필터링
                System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
                System.out.println(token.getMorph());
            }
        }

        return ResponseEntity.ok().body(articles);
    }
}
