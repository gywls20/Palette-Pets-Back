package com.palette.palettepetsback.Article.articleWrite.dto.request;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageDto {
    private String imgUrl;
    private Long articleId;
}
