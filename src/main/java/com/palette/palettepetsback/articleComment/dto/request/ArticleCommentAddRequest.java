package com.palette.palettepetsback.articleComment.dto.request;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.articleComment.entity.ArticleComment;
import com.palette.palettepetsback.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArticleCommentAddRequest {

    private Long createdWho;
    private Long articleId;
    @NotBlank(message = "content 비워둘수없음 ")
    private String content;
    private int parentId;
    private int ref;

    public ArticleComment toEntity(Article article,Member member,ArticleComment parentComment){
        return ArticleComment.builder()
                .article(article)
                .member(member)
                .parentId(parentComment)
                .content(this.getContent())
                .ref(this.ref)
                .build();
    }
}

