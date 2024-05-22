package com.palette.palettepetsback.articleView.controller;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    @Autowired
    private final ArticleService articleService;
    @GetMapping("/test")
    public ResponseEntity<List<Article>> list() {
        return ResponseEntity.ok().body(articleService.getList());
    }

    @GetMapping("/good")
    public String a() {
        return "her";
    }
}
