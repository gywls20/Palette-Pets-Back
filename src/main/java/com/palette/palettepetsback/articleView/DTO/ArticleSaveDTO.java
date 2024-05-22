package com.palette.palettepetsback.articleView.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSaveDTO {
    @NotEmpty
    private String content;
    private String tags;
}
