package com.palette.palettepetsback.articleComment.entity;

import com.palette.palettepetsback.articleWrite.entity.ArticleWrite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="article_comment")
@Getter
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_comment_id")
    private Long articleCommentId;
    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩 지연로딩 하면좋음
    @JoinColumn(name="article_id")
    private ArticleWrite articleWrite;
    @Column(name="created_who")
    private Long createdWho;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    private int ref;
    @Column(name="parent_id")
    private int parentId;
    @Column(name="content",nullable = false)
    private String content;
    @Column(name="is_deleted" ,nullable = false)
    private boolean isDeleted=false;

}


