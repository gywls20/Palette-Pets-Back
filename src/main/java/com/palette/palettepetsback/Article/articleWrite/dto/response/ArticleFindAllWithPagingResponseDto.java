package com.palette.palettepetsback.Article.articleWrite.dto.response;

import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleSimpleDto;
import com.palette.palettepetsback.Article.articleWrite.dto.request.PageInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleFindAllWithPagingResponseDto {
    private List<ArticleSimpleDto> articles;
    private PageInfoDto pageInfoDto;

    public static ArticleFindAllWithPagingResponseDto toDto(List<ArticleSimpleDto>articles,PageInfoDto pageInfoDto){
        return new ArticleFindAllWithPagingResponseDto(articles,pageInfoDto);
    }
}
