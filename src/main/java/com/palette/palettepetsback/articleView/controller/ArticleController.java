package com.palette.palettepetsback.articleView.controller;

import com.palette.palettepetsback.articleView.DTO.PageableDTO;
import com.palette.palettepetsback.articleView.entity.Article;
import com.palette.palettepetsback.articleView.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @GetMapping("/list")
    public ResponseEntity<?> getList(@ModelAttribute PageableDTO pageableDTO) {
        String direction = pageableDTO.getDir().equals("desc") ? "desc" : "asc";
        PageRequest pageRequest = PageRequest.of(pageableDTO.getPage(),
                         10,
                                  Sort.by(Sort.Direction.fromString(direction),
                                  pageableDTO.getSort()));

        Page<Article> articles = articleService.getList(pageRequest);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
