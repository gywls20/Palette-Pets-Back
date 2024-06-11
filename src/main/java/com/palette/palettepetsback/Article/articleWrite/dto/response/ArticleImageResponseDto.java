package com.palette.palettepetsback.Article.articleWrite.dto.response;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageResponseDto {

    private Long imgArticleId;
    private String imgUrl;
    private Long articleId;

}
