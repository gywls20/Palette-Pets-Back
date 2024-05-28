package com.palette.palettepetsback.articleComment.dto.request;

import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ArticleCommentDto {
    private Long articleCommentId;
    private Long articleId;
    private Long createdWho;
    private String content;


    public static ArticleCommentDto createArticleCommentDto(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getArticleCommentId(), //댓글 엔티티의 id
                articleComment.getArticle().getArticleId(),//댓글 엔티티가 속한 부모 게시글의 id\
                articleComment.getCreatedWho(),//댓글 엔티티의 createdWho
                articleComment.getContent() //댓글 엔티티의 content
        );
    }
}
