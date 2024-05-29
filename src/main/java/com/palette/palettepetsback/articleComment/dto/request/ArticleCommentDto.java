package com.palette.palettepetsback.articleComment.dto.request;

import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArticleCommentDto {
    private Long articleCommentId;
    private Long articleId;
    @NotNull(message = "createdWho null이 될수없음")
    private Long createdWho;
    @NotBlank(message = "content 비워둘수없음 ")
    private String content;
    private Long parentId;
    private ArticleCommentDto parentComment;
    private List<ArticleCommentDto> childComments;

    public ArticleCommentDto(
            Long articleCommentId,
            Long articleId,
            Long createdWho,
            String content,
            Long parentId,
            ArticleCommentDto parentComment) {
        this.articleCommentId = articleCommentId;
        this.articleId = articleId;
        this.createdWho = createdWho;
        this.content = content;
        this.parentId = parentId;
        this.parentComment = parentComment;
        this.childComments = new ArrayList<>(); // childComments 필드 초기화
    }


    public void setChildComments(List<ArticleCommentDto> childComments) {
        this.childComments = childComments;
    }


    public static ArticleCommentDto from(ArticleComment articleComment) {
        Long parentId = articleComment.getParentId();
        ArticleCommentDto parentComment = parentId != null
                ? new ArticleCommentDto(parentId, articleComment.getArticle().getArticleId(), articleComment.getCreatedWho(), articleComment.getContent(), null, null)
                : null;

        List<ArticleCommentDto> childComments = articleComment.getChildComments().stream()
                .map(ArticleCommentDto::from)
                .collect(Collectors.toList());

        return new ArticleCommentDto(
                articleComment.getArticleCommentId(),
                articleComment.getArticle().getArticleId(),
                articleComment.getCreatedWho(),
                articleComment.getContent(),
                parentId,
                (ArticleCommentDto) childComments
        );
    }


    //  패런트 아이디가 없으면 null 있으면 0 부모글 pk값 가져와서부모글
//    public static ArticleCommentDto createArticleCommentDto(ArticleComment articleComment) {
//        Long parentId = null; //기본값은 null
//        if (articleComment.getParentId() != null) {
//            parentId = articleComment.getParentId();//부모 댓글이 있는 경우 부모 댓글의 articleCommentId 가져오기
//        }
//        return new ArticleCommentDto(
//                articleComment.getArticleCommentId(), //댓글 엔티티의 id
//                articleComment.getArticle().getArticleId(),//댓글 엔티티가 속한 부모 게시글의 id
//                articleComment.getCreatedWho(),//댓글 엔티티의 createdWho
//                articleComment.getContent(), //댓글 엔티티의 content
//                parentId //부모 댓글 ID설정
//        );
//    }
}

