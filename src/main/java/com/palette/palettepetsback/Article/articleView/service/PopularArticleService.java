
package com.palette.palettepetsback.Article.articleView.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.articleView.DTO.response.PopularArticleDTO;
import com.palette.palettepetsback.Article.articleView.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PopularArticleService {
    private final ArticleRepository articleRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String POPULAR_POSTS_KEY = "popularPosts";

    // 10분마다 인기글 갱신 하여 redis에 저장
    public synchronized void likeArticle(){
        List<PopularArticleDTO> popularList = articleRepository.findPopularArticleByDate(LocalDateTime.now().minusDays(10));
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        redisTemplate.delete(POPULAR_POSTS_KEY); // 기존 키 삭제
        ops.rightPushAll(POPULAR_POSTS_KEY, popularList);
        redisTemplate.expire(POPULAR_POSTS_KEY, 10, TimeUnit.MINUTES);
    }

    // 인기글 조회
    public List<Object> getPopularPosts() {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        List<Object> popularList = ops.range(POPULAR_POSTS_KEY, 0, -1);
        // 인기글이 없을 경우 인기글 갱신
        if(popularList==null || popularList.isEmpty()){
            likeArticle();
            popularList = ops.range(POPULAR_POSTS_KEY, 0, -1);
        }

        return popularList;
    }
}

