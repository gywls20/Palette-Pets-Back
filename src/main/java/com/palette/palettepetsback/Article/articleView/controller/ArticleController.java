package com.palette.palettepetsback.Article.articleView.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.Article.articleView.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    //@Autowired
    private final ArticleService articleService;
    private final Integer PAGE_SIZE;

    @GetMapping("/list")
    public ResponseEntity<?> getList(@ModelAttribute PageableDTO request) {
        int startPage = (request.getPage()-1) * PAGE_SIZE;
        Sort sort = Sort.by(Sort.Direction.ASC, request.getSort());
        Pageable pageable = PageRequest.of(startPage,startPage + 10, sort);

        Page<Article> articles = articleService.getList(pageable);

        return ResponseEntity.ok().body(articles);
    }
}
