package com.palette.palettepetsback.Article.articleWrite.dto.request;

import com.palette.palettepetsback.Article.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSimpleDto {

    private Long articleId;
    private String title;
    private String memberNickname;
    private String articleTags;
    private Integer countLoves;

    public static ArticleSimpleDto toDto (Article article){
        return  new ArticleSimpleDto(
                article.getArticleId(),
                article.getTitle(),
                article.getMember().getMemberNickname(),
                article.getArticleTags(),
                article.getCountLoves()
        );
    }
}
