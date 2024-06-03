package com.palette.palettepetsback.articleComment.entity;

import com.palette.palettepetsback.Article.Article;

import com.palette.palettepetsback.articleComment.dto.request.ArticleCommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="article_comment")
@Getter
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_id")
    private Long articleCommentId;
    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩 지연로딩 하면좋음
    @JoinColumn(name = "article_id")
    private Article article;
    @Column(name = "created_who")
    private Long createdWho;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private int ref;
    @Column(name = "parent_id")
    private int parentId;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;


    public void setContent(String content) {
        this.content = content;
    }



    public static ArticleComment createComment(ArticleCommentDto articleCommentDto, Article article, ArticleComment parentComment) {
        if (articleCommentDto.getArticleId() != article.getArticleId())
            throw new IllegalArgumentException("댓글 생성 실패 게시글의 id가 잘못됐습니다");

        // 부모 댓글이 있는 경우
        if (parentComment != null) {
            // 자식 댓글의 ref 값 계산
            int childRef = parentComment.getRef() * 10 + 1;

            // 자식 댓글 생성
            return ArticleComment.builder()
                    .article(article)
                    .createdWho(articleCommentDto.getCreatedWho())
                    .content(articleCommentDto.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .ref(childRef)
                    .parentId(parentComment.getArticleCommentId().intValue())
                    .build();
        }
        // 부모 댓글이 없는 경우
        else {
            // 최상위 댓글 생성
            return ArticleComment.builder()
                    .article(article)
                    .createdWho(articleCommentDto.getCreatedWho())
                    .content(articleCommentDto.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .ref(1)
                    .parentId(0) //parentId가 0이면 부모가 없는 최상위 댓글임
                    .build();
        }
    }


}
