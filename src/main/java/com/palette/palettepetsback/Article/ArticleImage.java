package com.palette.palettepetsback.Article;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="img_article")
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="img_id")
    private Long id;
    @Column(name="img_url")
    private String imgUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @Builder
    public ArticleImage(String imgUrl, Article article){
        this.imgUrl = imgUrl;
        this.article = article;
    }

    public static ArticleImage from(String originalFilename) {
        return new ArticleImage(originalFilename, null);
    }

    public void initArticle(Article article) {
        if(this.article == null){
            this.article = article;
        }
    }

    public boolean isSameImageId(int id) {
        return this.getId() ==id;
    }
}
