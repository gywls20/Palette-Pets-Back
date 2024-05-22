package com.palette.palettepetsback.articleView.controller;

import com.palette.palettepetsback.articleView.dto.ArticleSaveDTO;
import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.service.ArticleServiceExam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final ArticleServiceExam articleService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, World!";
    }

    @PostMapping("/save/article")
    public ResponseEntity<?> saveArticle(@RequestBody ArticleSaveDTO request){
        Article article = articleService.saveArticle(Article.builder()
                .content(request.getContent())
                .created_who(1L)
                .article_tags(request.getTags())
                .build());
        return ResponseEntity.ok().body(article);
    }
}
