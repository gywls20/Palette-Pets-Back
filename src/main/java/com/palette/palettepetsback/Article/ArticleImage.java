package com.palette.palettepetsback.Article;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
