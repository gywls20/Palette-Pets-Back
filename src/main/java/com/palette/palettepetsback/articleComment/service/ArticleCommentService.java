package com.palette.palettepetsback.articleComment.service;

import com.palette.palettepetsback.articleComment.repository.ArticleCommentRepository;
import com.palette.palettepetsback.articleWrite.repository.ArticleWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)// 트랜잭션 걸어서 JPA가 제대로 돌아가게  함 ->  읽기만 가능 수정할거면 메서드에 각각 트랜잭션 걸기
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleWriteRepository articleWriteRepository;

}
