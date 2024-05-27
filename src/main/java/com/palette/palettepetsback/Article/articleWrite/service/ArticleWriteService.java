package com.palette.palettepetsback.Article.articleWrite.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleWriteDto;

import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ArticleWriteService {
    @Autowired
    private ArticleWriteRepository articleWriteRepository;

    //get
    public List<Article> index() {
        return articleWriteRepository.findAll();
    }

    @Transactional
    public Article create(ArticleWriteDto dto) {
        Article articleWrite = Article.builder()
                .createdWho(dto.getArticleId())
                .articleTags(dto.getArticleTags())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        if(articleWrite.getArticleId()!=null){
            return null;
        }
        return articleWriteRepository.save(articleWrite);
    }

    @Transactional

    public Article update(Long id, ArticleWriteDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Article articleWrite = Article.builder()
                .createdWho(dto.getCreatedWho())
                .title(dto.getTitle())
                .content(dto.getContent())
                .articleTags(dto.getArticleTags()   )
                .build();
        log.info("id:{}, articleWrite:{}", id, articleWrite.toString());

        // 2. 타깃 조회
        Article target = articleWriteRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null) {
            log.info("잘못된 요청! id:{}, articleWrite:{}", id, articleWrite.toString());
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 4. 업데이트하기
        target.patch(articleWrite);

        // 5 .상태 업데이트
        target.setState("MODIFIED");

        // 6. 수정된 엔티티 저장
        Article updated = articleWriteRepository.save(target); // 수정된 부분: target을 저장

        return updated; // 응답은 컨트롤러가 하므로 여기서는 수정된 데이터만 반환
    }

    //DELETE
    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleWriteRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        articleWriteRepository.delete(target);
        return target; //DB에서 삭제한 대상을 컨트롤러에 반환
    }
}



