package com.palette.palettepetsback.articleComment.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.service.ArticleService;
import com.palette.palettepetsback.articleComment.dto.request.ArticleCommentDto;
import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import com.palette.palettepetsback.articleComment.repository.ArticleCommentRepository;
import com.palette.palettepetsback.articleComment.service.ArticleCommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.palette.palettepetsback.Article.QArticle.article;

@RestController
@RequiredArgsConstructor
@CrossOrigin //리액트에서 넘어올때 포트가 다르면 오류가 생기는걸 해결해줌
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;
    private final ArticleService articleService;
    private final ArticleCommentRepository articleCommentRepository;


    //Get
    @GetMapping("/Get/comments/{articleId}")
    public ResponseEntity<List<ArticleCommentDto>> comments(@Valid @PathVariable Long articleId) {
        //서비스에 위임
        Article article = articleService.findById(articleId);
        List<ArticleCommentDto> dtos = articleCommentService.comments(article);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //POST
    @PostMapping("/Post/comments/{articleId}")
    public ResponseEntity<ArticleCommentDto>create(@Valid  @PathVariable Long articleId, @RequestBody ArticleCommentDto dto) {
        ArticleCommentDto createdDto = articleCommentService.create(articleId,dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }



   //PATCH
    @PatchMapping("/Patch/comments/{articleCommentId}")
    public ResponseEntity<ArticleCommentDto>update(@PathVariable Long articleCommentId,
                                                   @RequestBody ArticleCommentDto dto){
        ArticleCommentDto updatedDto = articleCommentService.update(articleCommentId,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

   //DELETE
    @DeleteMapping("/Delete/comments/{articleCommentId}")
    public ResponseEntity<Void> delete(@PathVariable Long articleCommentId){
        //대상 찾기
        ArticleComment target = articleCommentRepository.findById(articleCommentId).orElse(null);
        //잘못된 요청 처리하기
        if(target == null){ //이미 삭제된 대상인지 확인
            return ResponseEntity.notFound().build();
        }
        //대상 삭제하기 대신 상태변경하기

        articleCommentRepository.delete(target);//변경된 상태 저장
        return ResponseEntity.noContent().build();
    }

}
