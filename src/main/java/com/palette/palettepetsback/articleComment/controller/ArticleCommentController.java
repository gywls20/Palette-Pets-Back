package com.palette.palettepetsback.articleComment.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.service.ArticleService;
import com.palette.palettepetsback.articleComment.dto.request.ArticleCommentDto;
import com.palette.palettepetsback.articleComment.service.ArticleCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin //리액트에서 넘어올때 포트가 다르면 오류가 생기는걸 해결해줌
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;
    private final ArticleService articleService;


    //Get
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<ArticleCommentDto>> comments(@PathVariable Long articleId) {
        //서비스에 위임
        Article article = articleService.findById(articleId);
        List<ArticleCommentDto> dtos = articleCommentService.comments(article);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //Create
//    @PostMapping("/comments")
//    public ResponseEntity <ArticleCommentDto> createArticleComment(@RequestBody ArticleCommentDto articleCommentDto){
//        return null;
//    }


//    //PATCH
//    @PatchMapping("/comments/{commentId}")
//    public ResponseEntity<ArticleCommentDto>updateComment(@PathVariable Long commentId,@RequestBody ArticleCommentDto articleCommentDto){
//        return null;
//    }

//    //DELETE
//    @DeleteMapping("/comments/{commentId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
//        return null;
//    }

}
