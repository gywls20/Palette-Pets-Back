package com.palette.palettepetsback.articleComment.dto.request;

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


}
