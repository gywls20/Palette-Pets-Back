package com.palette.palettepetsback.articleView.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_id;

    @Column(name="created_who")
    private Long created_who;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(name = "article_tags")
    private String article_tags;

    @Column(name = "state")
    private String state;

    @Column(name = "count_loves")
    private Integer count_loves;

    @Column(name = "count_report")
    private Integer count_report;

    @Column(name = "count_views")
    private Integer count_views;
    @Column(name = "count_review")
    private Integer count_review;

    @Column(name="is_deleted", nullable = false)
    private Integer is_deleted;

    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
        this.count_loves = 0;
        this.count_report = 0;
        this.count_review = 0;
        this.count_views = 0;
        this.is_deleted = 0;
        this.state = "ACTIVE";
    }
}
