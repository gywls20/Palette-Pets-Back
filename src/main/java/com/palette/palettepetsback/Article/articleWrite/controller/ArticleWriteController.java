package com.palette.palettepetsback.Article.articleWrite.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleWriteDto;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import com.palette.palettepetsback.Article.articleWrite.service.ArticleWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //리액트에서 넘어올때 포트가 다르면 오류가 생기는걸 해결해줌
@Log4j2
//@RequiredArgsConstructor //final이 붙은 필드들을 자동 생성해주는 생성자 -> Autowired안써도 되게 해주는거
public class ArticleWriteController {

    private final ArticleWriteService articleWriteService;

    @Autowired
    public ArticleWriteController(ArticleWriteService articleWriteService) {
        this.articleWriteService = articleWriteService;
    } // 이거 대신에 @RequiredArgsConstructor 이거를 붙이면 된다

    //GET
    @GetMapping("/Get/articleWrite")
    public List<Article> index(){
        log.info("index");
        return articleWriteService.index();
    }


    //POST
    @PostMapping(path="/Post/articleWrite")
    public ResponseEntity<Article> create(@Valid @RequestBody ArticleWriteDto dto){
        Article created = articleWriteService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


    //업데이트 할때는 Article.state는 modified(수정됨)article_id,title ,content,created_at 4개가 들어가서 수정
    //PATCH 수정
    @PatchMapping("/Patch/articleWrite/{id}")
    public ResponseEntity<Article> update( @PathVariable Long id,
                                                @Valid
                                              @RequestBody ArticleWriteDto dto
    ){
        Article updated = articleWriteService.update(id,dto); // 서비스를 통해 게시글 수정
        return (updated != null)?//수정되면 정상, 안되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // 삭제할때는 Article.state는 deleted (삭제됨) article.is_deleted는 1로 수정 article_id 만 있으면 됨
    //DELETE
    @DeleteMapping("/Delete/articleWrite/{id}")
    public Article delete(@PathVariable Long id){
        // 1. 대상 찾기
        Article target = articleWriteRepository.findById(id).orElse(null);
        // 2. 잘못된 요청처리하기
        if(target ==null ) {//이미 삭제된 대상인지 확인
            return null;
        }
        //3. 대상 삭제하기 대신  상태변경하기
        target.markAsDeleted();
        articleWriteRepository.save(target); //변경된 상태 저장
        return target;
    }

}


