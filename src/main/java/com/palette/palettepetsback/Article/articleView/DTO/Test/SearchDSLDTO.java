package com.palette.palettepetsback.Article.articleView.DTO.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDSLDTO {
    private int page;
    private String search;
    private String sort;
    private boolean asc;
}
