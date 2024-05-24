package com.palette.palettepetsback.articleView.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long articleId;

    @Column(name="created_who")
    private Long createdWho;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "article_tags")
    private String articleTags;

    @Column(name = "state")
    private String state;

    @Column(name = "count_loves")
    private Integer countLoves;

    @Column(name = "count_report")
    private Integer countReport;

    @Column(name = "count_views")
    private Integer countViews;
    @Column(name = "count_review")
    private Integer countReview;

    @Column(name="is_deleted", nullable = false)
    private Integer isDeleted;

    @PrePersist //Entity 실행 전 수행하는 마라미터로 default 값을 지정O
    public void prePersist(){
        this.created_at = LocalDateTime.now();
        this.countLoves = 0;
        this.countReport = 0;
        this.countReview = 0;
        this.countViews = 0;
        this.isDeleted = 0;
        this.state = "ACTIVE";
    }
}
