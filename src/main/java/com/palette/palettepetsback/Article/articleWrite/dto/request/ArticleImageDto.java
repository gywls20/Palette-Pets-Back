package com.palette.palettepetsback.Article.articleWrite.dto.request;

import com.palette.palettepetsback.Article.ArticleImage;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageDto {
    private String imgUrl;
    private Long articleId;

    public static ArticleImageDto toDto(ArticleImage articleImage){
        return new ArticleImageDto (articleImage.getImgUrl(),articleImage.getArticle().getArticleId());
    }
}
