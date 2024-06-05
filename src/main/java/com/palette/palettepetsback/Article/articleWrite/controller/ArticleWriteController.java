package com.palette.palettepetsback.Article.articleWrite.controller;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleImageDto;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleWriteDto;


import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import com.palette.palettepetsback.Article.articleWrite.service.ArticleWriteService;
import com.palette.palettepetsback.config.SingleTon.Singleton;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin //리액트에서 넘어올때 포트가 다르면 오류가 생기는걸 해결해줌
@Log4j2
@RequiredArgsConstructor //final이 붙은 필드들을 자동 생성해주는 생성자 -> Autowired안써도 되게 해주는거
public class ArticleWriteController {

    private final ArticleWriteService articleWriteService;
    private final ArticleWriteRepository articleWriteRepository;

//    @Autowired
//    public ArticleWriteController(ArticleWriteService articleWriteService, ArticleWriteRepository articleWriteRepository) {
//        this.articleWriteService = articleWriteService;
//        this.articleWriteRepository = articleWriteRepository;
//    } // 이거 대신에 @RequiredArgsConstructor 이거를 붙이면 된다

    //GET
    @GetMapping("/Get/article")
    public List<Article> index(){
        log.info("index");
        return articleWriteService.index();
    }

    //게시글 등록
    @PostMapping(path="/Post/article")
    public ResponseEntity<Article> create(@Valid @RequestPart("dto") ArticleWriteDto dto,
                                                 @RequestPart("files") List<MultipartFile> files){
        //글 정보 DB 등록 -> article table
        Article created = articleWriteService.create(dto);

        //object storage upload
        for(MultipartFile file: files){

            String fileName =  articleWriteService.uploadArticleImage("article/img",file);

            //글 이미지 정보 DB 등록 -> img_article table
            ArticleImageDto imageDto = new ArticleImageDto();
            imageDto.setArticleId(created.getArticleId());
            imageDto.setImgUrl(fileName);
            articleWriteService.createImgArticle(imageDto);

        }

        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //게시글 이미지 등록
    @PostMapping("/Post/img")
    public boolean createArticleImg(@RequestBody ArticleImageDto dto){
        return articleWriteService.createImgArticle(dto)!=null;
    }

    //글 하나만 가져오기 만들기 ** * * ** *  * * 받아오는거 article정보하고 aritlce Img정보까지 다 받아오기



    //업데이트 할때는 Article.state는 modified(수정됨)article_id,title ,content,created_at 4개가 들어가서 수정
    //게시글 수정
    @PatchMapping("/Patch/{id}")
    public ResponseEntity<Article> update( @PathVariable Long id,
                                                @Valid
                                              @RequestBody ArticleWriteDto dto){
        Article updated = articleWriteService.update(id,dto); // 서비스를 통해 게시글 수정
        return (updated != null)?//수정되면 정상, 안되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // 삭제할때는 Article.state는 deleted (삭제됨) article.is_deleted는 1로 수정 article_id 만 있으면 됨
    //DELETE
    @DeleteMapping("/Delete/{id}")
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

    //게시글 이미지 삭제
    @DeleteMapping("{id}/img")
    public boolean deleteArticleImg(@PathVariable ("id")Long id,@RequestBody List<Long>imgIds ){
        articleWriteService.deleteImgArticle(imgIds);
        return true;
    }


}


