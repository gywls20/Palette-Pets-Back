//package com.palette.palettepetsback.articleComment.dto.request;
//
//import com.palette.palettepetsback.articleComment.entity.ArticleComment;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class ArticleCommentDto {
//    private Long articleCommentId;
//    private Long articleId;
////    @NotNull(message = "createdWho null이 될수없음")
//    private Long createdWho;
//    @NotBlank(message = "content 비워둘수없음 ")
//    private String content;
//    private Long parentId;
//    private int ref;
//    private LocalDateTime createdAt;
//
//    //  패런트 아이디가 없으면 null 있으면 0 부모글 pk값 가져와서부모글
//    public static ArticleCommentDto createArticleCommentDto(ArticleComment articleComment) {
//
//        return new ArticleCommentDto(
//                articleComment.getArticleCommentId(), //댓글 엔티티의 id
//                articleComment.getArticle().getArticleId(),//댓글 엔티티가 속한 부모 게시글의 id
//                articleComment.getCreatedWho(),//댓글 엔티티의 createdWho
//                articleComment.getContent(), //댓글 엔티티의 content
//                articleComment.getParentId(), //부모 댓글 ID설정
//                articleComment.getRef(),
//                articleComment.getCreatedAt()
//        );
//    }
//
//}
//
