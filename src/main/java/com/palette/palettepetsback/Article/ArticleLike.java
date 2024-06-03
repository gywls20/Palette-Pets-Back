package com.palette.palettepetsback.Article;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article_like")
public class ArticleLike {

    @EmbeddedId
    private ArticleLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleId")
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "created_who", referencedColumnName = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public ArticleLike(Member member, Article article) {
        this.id = new ArticleLikeId(member.getMemberId(), article.getArticleId());
        this.article = article;
        this.member = member;
    }

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
    }
}
