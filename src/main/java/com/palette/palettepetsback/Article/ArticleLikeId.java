package com.palette.palettepetsback.Article;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleLikeId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "created_who")
    private Long memberId;

    @Column(name = "article_id")
    private Long articleId;

    // 접근자 메서드 추가

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleLikeId that = (ArticleLikeId) o;
        return Objects.equals(memberId, that.memberId) &&
                Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, articleId);
    }
}
