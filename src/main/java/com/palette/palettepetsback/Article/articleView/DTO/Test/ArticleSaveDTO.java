package com.palette.palettepetsback.Article.articleView.DTO.Test;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSaveDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String tags;
}
