package com.palette.palettepetsback.articleView.controller;

import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.repository.ArticleRepository;
import com.palette.palettepetsback.articleView.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private final ArticleService articleService;
    @GetMapping("/list")
    public ResponseEntity<Page<Article>> getList(@RequestParam(value = "page", defaultValue = "0" ) int page,
                                                 @RequestParam(value = "sort", defaultValue = "articleId") String sort,
                                                 @RequestParam(value = "dir", defaultValue = "desc") String dir) {
        PageRequest pageRequest = PageRequest.of(page, Sort.by(dir, sort));

        return ResponseEntity.ok().body(articleService.getList(pageRequest));
    }
}
