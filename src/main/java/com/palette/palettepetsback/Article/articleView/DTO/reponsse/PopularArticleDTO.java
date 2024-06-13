package com.palette.palettepetsback.Article.articleView.DTO.reponsse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularArticleDTO {
    private Long articleId;
    private String title;
    private Long userId;
    private String name;
}
