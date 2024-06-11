package com.palette.palettepetsback.Article.articleView.DTO.reponsse;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {

    private Long articleId;
    private LocalDateTime createdAt;
    private Long createdWho;
    private String content;
    private String title;
    private String articleTags;
    private String articleHead;
    private String boardName;
    private Integer countLoves;
    private Integer countViews;
    private Integer countReview;


    public ArticleResponseDTO(Article responseDTO) {

        this.articleId = responseDTO.getArticleId();
        this.createdAt = responseDTO.getCreatedAt();
        this.createdWho = responseDTO.getCreatedWho();
        this.content = responseDTO.getContent();
        this.title = responseDTO.getTitle();
        this.articleTags = responseDTO.getArticleTags();
        this.articleHead = responseDTO.getArticleHead();
        this.boardName = String.valueOf(responseDTO.getBoardName());
        this.countLoves = responseDTO.getCountLoves();
        this.countViews = responseDTO.getCountViews();
        this.countReview = responseDTO.getCountReview();

    }



}
