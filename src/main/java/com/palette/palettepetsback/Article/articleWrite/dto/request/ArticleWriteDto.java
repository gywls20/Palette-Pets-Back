package com.palette.palettepetsback.Article.articleWrite.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ArticleWriteDto {

    private Long articleId;
    private String articleTags;
    @Size(min =2,max = 20)
    private String title;
    @Size(min=10,max=1000)
    private String content;
    private Long createdWho;
}
