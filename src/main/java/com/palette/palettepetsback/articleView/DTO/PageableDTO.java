package com.palette.palettepetsback.articleView.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDTO {
    private int page = 0;
    private String sort = "article_id";
    private String dir = "desc";
}
