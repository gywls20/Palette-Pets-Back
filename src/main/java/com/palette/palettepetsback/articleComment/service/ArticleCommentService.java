package com.palette.palettepetsback.articleComment.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import com.palette.palettepetsback.articleComment.dto.request.ArticleCommentDto;
import com.palette.palettepetsback.articleComment.repository.ArticleCommentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)// 트랜잭션 걸어서 JPA가 제대로 돌아가게  함 ->  읽기만 가능 수정할거면 메서드에 각각 트랜잭션 걸기
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleWriteRepository articleWriteRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> comments(Article article) {
        return articleCommentRepository.findByArticle(article) //댓글 엔티티 목록 조회
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(ArticleCommentDto::createArticleCommentDto)//엔티티를 DTO로 매핑
                .collect(Collectors.toList()); //스트림을 리스트로 변환
    }


}
