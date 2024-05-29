package com.palette.palettepetsback.articleComment.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.QArticle;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import com.palette.palettepetsback.Article.articleView.service.ArticleService;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import com.palette.palettepetsback.articleComment.dto.request.ArticleCommentDto;
import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import com.palette.palettepetsback.articleComment.repository.ArticleCommentRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)// 트랜잭션 걸어서 JPA가 제대로 돌아가게  함 ->  읽기만 가능 수정할거면 메서드에 각각 트랜잭션 걸기
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

//    @Transactional(readOnly = true)
//    public List<ArticleCommentDto> comments(Article article) {
//        return articleCommentRepository.findByArticle(article) //댓글 엔티티 목록 조회
//                .stream() //댓글 엔티티 목록을 스트림으로 변환
//                .map(ArticleCommentDto::createArticleCommentDto)//엔티티를 DTO로 매핑
//                .collect(Collectors.toList()); //스트림을 리스트로 변환
//    }

    //댓글 조회
    @Transactional
    public List<ArticleCommentDto>comments(Article article){
        return articleCommentRepository.findByArticle(article)//댓글 엔티티 목록 조회
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(ArticleCommentDto::createArticleCommentDto)//엔티티를 DTO로 매핑
                .collect(Collectors.toList()); //스트림을 리스트로 변환
    }
    //댓글 작성
    @Transactional
    public ArticleCommentDto create(Long articleId,ArticleCommentDto dto){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));

        //부모 댓글이 있는 경우
        ArticleComment parentComment = null;
        if(dto.getParentId()!=null){
            parentComment = articleCommentRepository.findById(dto.getParentId())
                    .orElseThrow(()->new IllegalArgumentException("부모 댓글을 찾을 수 없습니다."));
        }

        ArticleComment articleComment = ArticleComment.createComment(dto,article,parentComment);
        ArticleComment created =articleCommentRepository.save(articleComment);
        return ArticleCommentDto.createArticleCommentDto(created);

    }


//@Transactional
//public ArticleCommentDto create(Long articleId, ArticleCommentDto dto) {
//    Article article = articleRepository.findById(articleId)
//            .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));
//    ArticleComment articleComment = ArticleComment.createComment(dto,article,null);
//    ArticleComment created = articleCommentRepository.save(articleComment);
//    return ArticleCommentDto.createArticleCommentDto(created);
//}




    //부모 댓글의 ref값을 기반으로 자식 댓글의 ref값을 계산
    private int calculateChildRef(int parentRef) {
        return parentRef * 10 + 1;
    }

    //댓글 수정
    @Transactional
    public ArticleCommentDto update(Long articleCommentId,ArticleCommentDto dto){
        //댓글 조회
        ArticleComment target = articleCommentRepository.findById(articleCommentId)
                .orElseThrow(()->new IllegalArgumentException("댓글을 찾을수 없습니다"));
        //댓글 내용 업데이트
        target.setContent(dto.getContent());
        //변경 사항저장
        ArticleComment updatedComment = articleCommentRepository.save(target);
        //DTO로 변환하여 반환
        return ArticleCommentDto.createArticleCommentDto(updatedComment);
    }
    //댓글 삭제
    public ArticleComment delete(Long articleCommentId){
        //대상찾기
        ArticleComment target = articleCommentRepository.findById(articleCommentId).orElse(null);
        //잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        //대상 삭제하기
        articleCommentRepository.delete(target);
        return target;
    }

//        @Transactional
//        public ArticleCommentDto update(Long articleCommentId,ArticleCommentDto dto){
//            //댓글 조회
//            ArticleComment target = articleCommentRepository.findById(articleCommentId)
//                    .orElseThrow(()->new IllegalArgumentException("댓글을 찾을 수 없습니다."));
//            // 댓글 내용 업데이트
//            target.setContent(dto.getContent());
//            //변경 사항 저장
//            ArticleComment updatedComment=articleCommentRepository.save(target);
//            //DTO로 변환하여 반환
//            return ArticleCommentDto.createArticleCommentDto(updatedComment);
//        }

//        //DELETE
//        public ArticleComment delete (Long ArticleCommentId){
//            //대상 찾기
//            ArticleComment target = articleCommentRepository.findById(ArticleCommentId).orElse(null);
//            //잘못된 요청 처리하기
//            if (target == null) {
//                return null;
//            }
//            //대상 삭제하기
//            articleCommentRepository.delete(target);
//            return target;
//        }
    }


