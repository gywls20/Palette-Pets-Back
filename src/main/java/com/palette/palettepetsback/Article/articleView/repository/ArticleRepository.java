package com.palette.palettepetsback.Article.articleView.repository;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.DTO.reponsse.PopularArticleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Query("SELECT count(a) FROM Article a WHERE a.articleTags LIKE %?1%")
    Optional<Integer> countByArticleTagsContaining(String tag);

    @Query("SELECT a.articleId, a.title, a.member.memberId, a.member.memberNickname " +
            "FROM Article as a JOIN FETCH a.member " +
            "where a.createdAt >= :date order by a.countLoves desc")
    List<PopularArticleDTO> findPopularArticleByDate(LocalDate date);
}
