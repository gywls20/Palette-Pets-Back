package com.palette.palettepetsback.Article.redis.repository;

import com.palette.palettepetsback.Article.redis.LikeArticleRedis;
import org.springframework.data.repository.CrudRepository;

public interface ArticleLikeRedisRepository extends CrudRepository<LikeArticleRedis,String> {
}
