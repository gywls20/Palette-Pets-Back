package com.palette.palettepetsback.Article.articleView.DTO.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDSLDTO {
    private int page;
    private String sort;
    private boolean asc;
}
