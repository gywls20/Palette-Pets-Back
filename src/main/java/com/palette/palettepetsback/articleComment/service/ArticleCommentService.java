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

    @Transactional
    public List<ArticleCommentDto> comments(Article article) {
        // 부모 댓글 가져오기
        List<ArticleComment> parentComments = articleCommentRepository.findByArticleAndParentIdIsNull(article);
        List<ArticleCommentDto> parentDtos = parentComments.stream()
                .map(ArticleCommentDto::from)
                .collect(Collectors.toList());

        // 각 부모 댓글에 대한 자식 댓글 가져와 설정
        for (ArticleCommentDto parentDto : parentDtos) {
            List<ArticleComment> childComments = articleCommentRepository.findByArticleAndParentId(article, parentDto.getArticleCommentId());
            parentDto.setChildComments(childComments.stream()
                    .map(ArticleCommentDto::from)
                    .collect(Collectors.toList()));
        }

        return parentDtos;
    }


//@Transactional
//public ArticleCommentDto create(Long articleId, ArticleCommentDto dto) {
//    Article article = articleRepository.findById(articleId)
//            .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));
//    ArticleComment articleComment = ArticleComment.createComment(dto,article,null);
//    ArticleComment created = articleCommentRepository.save(articleComment);
//    return ArticleCommentDto.createArticleCommentDto(created);
//}

    @Transactional
    public ArticleCommentDto create(Long articleId, ArticleCommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다"));

        ArticleComment articleComment;
        if (dto.getParentId() != null) {
            ArticleComment parent = articleCommentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 : 대상 부모 댓글이 없습니다."));
            articleComment = createChildComment(dto, article, parent);

        } else {
            articleComment = createTopLevelComment(dto, article);
        }
        ArticleComment savedComment = articleCommentRepository.save(articleComment);
        return ArticleCommentDto.from(savedComment);
    }

    //부모 댓글이 없는 경우 최상위 댓글 생성
    private ArticleComment createTopLevelComment(ArticleCommentDto dto, Article article) {
        return ArticleComment.builder()
                .article(article)
                .createdWho(dto.getCreatedWho())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .ref(1)
                .parentId(null)
                .build();
    }

    //부모 댓글이 있는 경우 대댓글 생성
    private ArticleComment createChildComment(ArticleCommentDto dto, Article article, ArticleComment parentComment) {
        int childRef = calculateChildRef(parentComment.getRef());
        return ArticleComment.builder()
                .article(article)
                .createdWho(dto.getCreatedWho())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .ref(childRef)
                .parentId((parentComment.getArticleCommentId()))
                .build();
    }

    //부모 댓글의 ref값을 기반으로 자식 댓글의 ref값을 계산
    private int calculateChildRef(int parentRef) {
        return parentRef * 10 + 1;
    }

//    @Transactional
//    public ArticleCommentDto update(Long articleCommentId, ArticleCommentDto dto) {
//        //댓글 조회
//        ArticleComment target = articleCommentRepository.findById(articleCommentId)
//                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
//
//        //댓글 내용 업데이트
//        target.setContent(dto.getContent());
//
//        //변경사항 저장
//        ArticleComment updatedComment = articleCommentRepository.save(target);
//
//        //DTO로 변환하여 반환
//        return createArticleCommentDto(updatedComment);
//    }

//    public static ArticleCommentDto createArticleCommentDto(ArticleComment articleComment) {
//        Long parentId = articleComment.getParentId();
//        ArticleCommentDto parentComment = parentId != null
//                ? new ArticleCommentDto(
//                parentId,
//                articleComment.getArticle().getArticleId(),
//                articleComment.getCreatedWho(),
//                articleComment.getContent(),
//                null,
//                null)
//                : null;
//
//        List<ArticleCommentDto> childComments = articleComment.getChildComments().stream()
//                .map(ArticleCommentDto::createArticleCommentDto)
//                .collect(Collectors.toList());
//
//        return new ArticleCommentDto(
//                articleComment.getArticleCommentId(),
//                articleComment.getArticle().getArticleId(),
//                articleComment.getCreatedWho(),
//                articleComment.getContent(),
//                parentId,
//                parentComment,
//                childComments
//        );


//@Transactional
//public ArticleCommentDto update(Long articleCommentId,ArticleCommentDto dto){
//    //댓글 조회
//    ArticleComment target = articleCommentRepository.findById(articleCommentId)
//            .orElseThrow(()->new IllegalArgumentException("댓글을 찾을 수 없습니다."));
//    // 댓글 내용 업데이트
//    target.setContent(dto.getContent());
//    //변경 사항 저장
//    ArticleComment updatedComment=articleCommentRepository.save(target);
//    //DTO로 변환하여 반환
//    return ArticleCommentDto.createArticleCommentDto(updatedComment);
//}

//DELETE
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

