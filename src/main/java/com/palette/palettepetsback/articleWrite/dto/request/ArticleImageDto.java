package com.palette.palettepetsback.articleWrite.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticleImageDto {
    private String img;
    private Long article_id;
}
