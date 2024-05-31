package com.palette.palettepetsback.Article.articleWrite.dto.response;

import com.palette.palettepetsback.Article.ArticleLike;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikeResponseDto {
    private Long id;
    private Long articleId;
    private Long memberId;
    private LocalDateTime createdAt;
}
